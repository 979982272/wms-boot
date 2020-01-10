package com.tudou.crm.purchase.purchaseOrder.service.impl;

import com.tudou.crm.purchase.purchaseApply.entity.PurchaseApply;
import com.tudou.crm.purchase.purchaseApply.entity.PurchaseApplyPart;
import com.tudou.crm.purchase.purchaseApply.mapper.PurchaseApplyMapper;
import com.tudou.crm.purchase.purchaseApply.service.IPurchaseApplyPartService;
import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrder;
import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrderPart;
import com.tudou.crm.purchase.purchaseOrder.mapper.PurchaseOrderMapper;
import com.tudou.crm.purchase.purchaseOrder.service.IPurchaseOrderPartService;
import com.tudou.crm.purchase.purchaseOrder.service.IPurchaseOrderService;
import com.tudou.crm.storage.inStorage.service.IInStorageWorkService;
import com.tudou.extra.enums.OrderTypeEnum;
import com.tudou.extra.enums.purchase.PurchaseApplyStatusEnum;
import com.tudou.extra.enums.purchase.PurchaseOrderStatusEnum;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import com.tudou.util.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl extends BaseServiceImpl<PurchaseOrder, String> implements IPurchaseOrderService {

    @Resource
    @BaseResource
    private PurchaseOrderMapper purchaseOrderMapper;

    @Resource
    private PurchaseApplyMapper purchaseApplyMapper;

    @Resource
    private IPurchaseOrderPartService purchaseOrderPartService;

    @Resource
    private IPurchaseApplyPartService purchaseApplyPartService;

    @Resource
    private IInStorageWorkService inStorageWorkService;

    @Override
    public void updatePurchaseOrderStatus(String fromOrder) throws Exception {
        Integer successReceivingCount = 0;
        Integer partReceivingCount = 0;
        Integer receivingStatus = null;
        PurchaseOrder purchaseOrder = purchaseOrderMapper.findPurchaseOrderById(fromOrder);
        List<PurchaseOrderPart> purchaseOrderParts = purchaseOrder.getPurchaseOrderParts();
        for (PurchaseOrderPart part : purchaseOrderParts) {
            if (part.getReceivingAmount().compareTo(part.getPurchaseAmount()) >= 0) {
                // 下推数量 大于申请数量的记录数
                // 如果改记录数等于明细数量则表名完全下推，否则部分下推
                successReceivingCount = successReceivingCount + 1;
            }
            if (part.getReceivingAmount().compareTo(BigDecimal.ZERO) > 0) {
                partReceivingCount = partReceivingCount + 1;
            }
        }
        if (successReceivingCount == purchaseOrderParts.size()) {
            // 完全下推的数量等于明细数量：完全下推
            receivingStatus = PurchaseOrderStatusEnum.SUCCESSRECEIVING.getCode();
        } else if (partReceivingCount > 0) {
            // 部分下推的数量大于明细数量：部分下推
            receivingStatus = PurchaseOrderStatusEnum.PARTRECEIVING.getCode();
        } else {
            receivingStatus = 0;
        }
        // 如果下推状态等于零则表示错误调用不做处理
        if (receivingStatus != 0) {
            purchaseOrderMapper.updatePurchaseOrderStatusById(fromOrder, receivingStatus);
        }
    }

    @Override
    public void auditById(String id) throws Exception {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.findPurchaseOrderById(id);
        if (null == purchaseOrder) {
            throw new Exception("查询不到对应的采购订单！【" + id + "】");
        }
        if (CollectionUtils.isEmpty(purchaseOrder.getPurchaseOrderParts())) {
            throw new Exception("查询不到对应的采购订单明细！【" + id + "】");
        }
        purchaseOrder.setStatus(PurchaseOrderStatusEnum.AUDIT.getCode());
        super.update(purchaseOrder);
        // 生成入库作业单
        inStorageWorkService.buidInStorageWordkByPurchaseOrder(purchaseOrder);
    }

    @Override
    public PurchaseOrder savePurchaseOrder(PurchaseOrder t) throws Exception {
        t.setStatus(PurchaseOrderStatusEnum.CREATE.getCode());
        super.save(t);
        purchaseOrderPartService.savePurchaseApplyPart(t.getPurchaseOrderParts(), t.getId());
        return t;
    }

    @Override
    public void savePurchaseOrderByApplyPush(PurchaseOrder purchaseOrder) throws Exception {
        PurchaseApply purchaseApply = purchaseApplyMapper.findPurchaseApplyById(purchaseOrder.getFromOrder());
        if (null == purchaseApply) {
            throw new Exception("查询不到对应的采购申请单！【" + purchaseOrder.getFromOrder() + "】");
        }
        if (purchaseApply.getStatus() > PurchaseApplyStatusEnum.PARTPUSH.getCode()) {
            throw new Exception("采购申请单已完全下推！【" + purchaseApply.getId() + "】");
        }
        super.save(purchaseOrder);
        // 可以下推的数量
        Map<String, BigDecimal> pushAmountMap = new HashMap<>();
        // 对应的采购申请明细
        Map<String, PurchaseApplyPart> applyPartMap = new HashMap<>();
        for (PurchaseApplyPart part : purchaseApply.getPurchaseApplyParts()) {
            pushAmountMap.put(part.getGoodsId(), part.getApplyAmount().subtract(part.getPushAmount()));
            applyPartMap.put(part.getGoodsId(), part);
        }
        PurchaseApplyPart purchaseApplyPart = null;
        // 保存采购订单明细
        for (PurchaseOrderPart part : purchaseOrder.getPurchaseOrderParts()) {
            BigDecimal pushAmount = pushAmountMap.get(part.getGoodsId());
            if (part.getPurchaseAmount().compareTo(pushAmount) > 0) {
                throw new Exception("产品【" + part.getGoodsId() + "】的下推数量大于可下推数【" + pushAmount + "】");
            }
            purchaseOrderPartService.save(part);
            purchaseApplyPart = applyPartMap.get(part.getGoodsId());
            // 回写采购申请明细中的可下推数量
            purchaseApplyPart.setPushAmount(purchaseApplyPart.getPushAmount().add(part.getPurchaseAmount()));
            purchaseApplyPartService.update(purchaseApplyPart);
        }
        reWritePurchaseApplyStatue(purchaseOrder.getFromOrder());
    }

    /**
     * 回写采购申请单的状态
     *
     * @param purchaseApplyId
     */
    private void reWritePurchaseApplyStatue(String purchaseApplyId) {
        Integer successPushCount = 0;
        Integer partPushCount = 0;
        Integer pushStatus = null;
        PurchaseApply purchaseApply = purchaseApplyMapper.findPurchaseApplyById(purchaseApplyId);
        List<PurchaseApplyPart> purchaseApplyParts = purchaseApply.getPurchaseApplyParts();
        for (PurchaseApplyPart part : purchaseApplyParts) {
            if (part.getPushAmount().compareTo(part.getApplyAmount()) >= 0) {
                // 下推数量 大于申请数量的记录数
                // 如果改记录数等于明细数量则表名完全下推，否则部分下推
                successPushCount = successPushCount + 1;
            }
            if (part.getPushAmount().compareTo(BigDecimal.ZERO) > 0) {
                partPushCount = partPushCount + 1;
            }
        }
        if (successPushCount == purchaseApplyParts.size()) {
            // 完全下推的数量等于明细数量：完全下推
            pushStatus = PurchaseApplyStatusEnum.SUCCESSPUSH.getCode();
        } else if (partPushCount > 0) {
            // 部分下推的数量大于明细数量：部分下推
            pushStatus = PurchaseApplyStatusEnum.PARTPUSH.getCode();
        } else {
            pushStatus = 0;
        }
        // 如果下推状态等于零则表示错误调用不做处理
        if (pushStatus != 0) {
            purchaseApplyMapper.updatePurchaseApplyStatusById(purchaseApplyId, pushStatus);
        }
    }

    @Override
    public PurchaseOrder buidPurchaseOrderByApplyId(String id) throws Exception {
        PurchaseApply purchaseApply = purchaseApplyMapper.findPurchaseApplyById(id);
        if (null == purchaseApply) {
            throw new Exception("查询不到对应的采购申请单！【" + id + "】");
        }
        if (CollectionUtils.isEmpty(purchaseApply.getPurchaseApplyParts())) {
            throw new Exception("查询不到采购申请单明细！【" + id + "】");
        }
        return buidPurchaseByApply(purchaseApply);
    }

    /**
     * 组合采购订单
     *
     * @param purchaseApply
     * @return
     */
    private PurchaseOrder buidPurchaseByApply(PurchaseApply purchaseApply) {
        PurchaseOrder order = new PurchaseOrder();
        String id = CommonUtil.getIdByCode(OrderTypeEnum.PURCHASEORDER.getCode());
        order.setId(id);
        order.setFromOrder(purchaseApply.getId());
        order.setVendorId(purchaseApply.getVendorId());
        order.setVendorName(purchaseApply.getVendorName());
        order.setWarehouseId(purchaseApply.getWarehouseId());
        order.setWarehouseName(purchaseApply.getWarehouseName());
        order.setStatus(PurchaseOrderStatusEnum.CREATE.getCode());
        order.setOrderDate(new Date());
        order.setRemark(purchaseApply.getRemark());
        List<PurchaseOrderPart> purchaseOrderParts = buidPurchaseOrderPartByApplyPart(purchaseApply.getPurchaseApplyParts(), order.getId());
        order.setPurchaseOrderParts(purchaseOrderParts);
        return order;
    }

    /**
     * 组合采购订单明细
     *
     * @param purchaseApplyParts
     * @return
     */
    private List<PurchaseOrderPart> buidPurchaseOrderPartByApplyPart(List<PurchaseApplyPart> purchaseApplyParts, String purchaseOrderId) {
        List<PurchaseOrderPart> purchaseOrderParts = new ArrayList<>();
        PurchaseOrderPart orderPart = null;
        for (PurchaseApplyPart part : purchaseApplyParts) {
            orderPart = new PurchaseOrderPart();
            orderPart.setFromOrderPart(part.getId());
            orderPart.setPurchaseOrderId(purchaseOrderId);
            orderPart.setGoodsId(part.getGoodsId());
            orderPart.setGoodsName(part.getGoodsName());
            orderPart.setGoodsUnitId(part.getGoodsUnitId());
            orderPart.setGoodsUnitName(part.getGoodsUnitName());
            orderPart.setGoodsModel(part.getGoodsModel());
            orderPart.setPurchaseAmount(part.getApplyAmount().subtract(part.getPushAmount()));
            orderPart.setReceivingAmount(BigDecimal.ZERO);
            orderPart.setUnitPrice(part.getUnitPrice());
            orderPart.setRate(part.getRate());
            purchaseOrderParts.add(orderPart);
        }
        return purchaseOrderParts;
    }
}