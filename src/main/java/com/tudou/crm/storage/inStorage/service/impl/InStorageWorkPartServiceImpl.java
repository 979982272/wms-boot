package com.tudou.crm.storage.inStorage.service.impl;

import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrderPart;
import com.tudou.crm.purchase.purchaseOrder.mapper.PurchaseOrderPartMapper;
import com.tudou.crm.purchase.purchaseOrder.service.IPurchaseOrderPartService;
import com.tudou.crm.purchase.purchaseOrder.service.IPurchaseOrderService;
import com.tudou.crm.stock.stockPart.service.IStockPartService;
import com.tudou.crm.stock.stockTrade.service.IStockTradeService;
import com.tudou.crm.storage.inStorage.entity.InStorageWork;
import com.tudou.crm.storage.inStorage.entity.InStorageWorkPart;
import com.tudou.crm.storage.inStorage.mapper.InStorageWorkMapper;
import com.tudou.crm.storage.inStorage.mapper.InStorageWorkPartMapper;
import com.tudou.crm.storage.inStorage.service.IInStorageWorkPartService;
import com.tudou.crm.storage.inStorage.service.IInStorageWorkService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("inStorageWorkPartService")
public class InStorageWorkPartServiceImpl extends BaseServiceImpl<InStorageWorkPart, String> implements IInStorageWorkPartService {

    @Resource
    @BaseResource
    private InStorageWorkPartMapper inStorageWorkPartMapper;

    @Resource
    private PurchaseOrderPartMapper purchaseOrderPartMapper;

    @Resource
    private IPurchaseOrderPartService purchaseOrderPartService;

    @Resource
    private InStorageWorkMapper inStorageWorkMapper;

    @Resource
    private IStockPartService stockPartService;

    @Resource
    private IStockTradeService stockTradeService;

    @Resource
    private IInStorageWorkService iInStorageWorkService;

    @Resource
    private IPurchaseOrderService purchaseOrderService;

    @Override
    public void inStorage(List<InStorageWorkPart> inStorageWorkParts) throws Exception {
        if (CollectionUtils.isEmpty(inStorageWorkParts)) {
            throw new Exception("入库单明细不能为空！");
        }
        List<PurchaseOrderPart> purchaseOrderParts = purchaseOrderPartMapper.findPurchaseOrderPartByPartId(inStorageWorkParts.get(0).getFromOrderPart());
        if (CollectionUtils.isEmpty(purchaseOrderParts)) {
            throw new Exception("对应的采购单明细为空！");
        }
        if (purchaseOrderParts.size() != inStorageWorkParts.size()) {
            throw new Exception("对应的采购单明细与入库单明细数量不相等！");
        }
        Map<String, PurchaseOrderPart> purchaseOrderPartMap = new HashMap<>();
        for (PurchaseOrderPart part : purchaseOrderParts) {
            purchaseOrderPartMap.put(part.getId(), part);
        }
        for (InStorageWorkPart part : inStorageWorkParts) {
            inStorage(part, purchaseOrderPartMap.get(part.getFromOrderPart()), null);
        }
    }

    @Override
    public void inStorageByPart(String id, BigDecimal receivingAmount) throws Exception {
        InStorageWorkPart inStorageWorkPart = selectById(id);
        if (null == inStorageWorkPart) {
            throw new Exception("查询不到对应的明细信息!【" + id + "】");
        }
        PurchaseOrderPart part = purchaseOrderPartService.selectById(inStorageWorkPart.getFromOrderPart());
        /**
         * 一、 1.修改入库单入库数量
         *      2.修改对应的采购单明细的收货数量
         *      3.生成库存
         *      4.生成交易日志
         * 二、 1.修改入库单状态
         * 三、 1.修改采购单状态
         */
        inStorage(inStorageWorkPart, part, receivingAmount);
        InStorageWork storageWork = inStorageWorkMapper.findInStorageWorkById(inStorageWorkPart.getInStorageWork());
        iInStorageWorkService.updateInStorageWorkStatus(storageWork);
        purchaseOrderService.updatePurchaseOrderStatus(storageWork.getFromOrder());
    }

    /**
     * 整单入库
     *
     * @param part
     * @throws Exception
     */
    public void inStorage(InStorageWorkPart part, PurchaseOrderPart purchaseOrderPart, BigDecimal receivingAmount) throws Exception {
        if (part == null) {
            throw new Exception("入库单明细为空！");
        }
        if (purchaseOrderPart == null) {
            throw new Exception("采购单明细为空!");
        }
        /**
         * 1.修改入库单入库数量
         * 2.修改对应的采购单明细的收货数量
         * 3.生成库存
         * 4.生成交易日志
         */
        if (receivingAmount == null || receivingAmount.compareTo(BigDecimal.ZERO) == 0) {
            receivingAmount = part.getPlanAmount().subtract(part.getReceivingAmount());
        }
        part.setReceivingAmount(part.getReceivingAmount().add(receivingAmount));
        super.update(part);
        purchaseOrderPart.setReceivingAmount(purchaseOrderPart.getReceivingAmount().add(receivingAmount));
        purchaseOrderPartService.update(purchaseOrderPart);
        // 查询主单
        InStorageWork storageWork = inStorageWorkMapper.selectByPrimaryKey(part.getInStorageWork());
        stockPartService.saveStockPartByInStoragePart(storageWork, part, receivingAmount);
        stockTradeService.saveStockTradeByInStoragePart(storageWork, part, receivingAmount);
    }

    @Override
    public void buidStorageWorkPartByPurchaseOrderPart(List<PurchaseOrderPart> purchaseOrderParts, String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            throw new Exception("入库单号不能为空！");
        }
        if (CollectionUtils.isEmpty(purchaseOrderParts)) {
            throw new Exception("采购单明细不能为空！");
        }
        InStorageWorkPart storageWorkPart = null;
        for (PurchaseOrderPart part : purchaseOrderParts) {
            storageWorkPart = new InStorageWorkPart();
            storageWorkPart.setFromOrderPart(part.getId());
            storageWorkPart.setInStorageWork(id);
            storageWorkPart.setGoodsId(part.getGoodsId());
            storageWorkPart.setGoodsName(part.getGoodsName());
            storageWorkPart.setGoodsUnitId(part.getGoodsUnitId());
            storageWorkPart.setGoodsUnitName(part.getGoodsUnitName());
            storageWorkPart.setGoodsModel(part.getGoodsModel());
            storageWorkPart.setPlanAmount(part.getPurchaseAmount());
            storageWorkPart.setReceivingAmount(BigDecimal.ZERO);
            storageWorkPart.setRemark(part.getRemark());
            super.save(storageWorkPart);
        }
    }
}