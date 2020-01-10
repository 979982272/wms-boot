package com.tudou.crm.storage.inStorage.service.impl;

import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrder;
import com.tudou.crm.purchase.purchaseOrder.service.IPurchaseOrderService;
import com.tudou.crm.storage.inStorage.entity.InStorageWork;
import com.tudou.crm.storage.inStorage.entity.InStorageWorkPart;
import com.tudou.crm.storage.inStorage.mapper.InStorageWorkMapper;
import com.tudou.crm.storage.inStorage.service.IInStorageWorkPartService;
import com.tudou.crm.storage.inStorage.service.IInStorageWorkService;
import com.tudou.extra.enums.OrderTypeEnum;
import com.tudou.extra.enums.storage.StorageStatusEnum;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import com.tudou.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("inStorageWorkService")
public class InStorageWorkServiceImpl extends BaseServiceImpl<InStorageWork, String> implements IInStorageWorkService {

    @Resource
    @BaseResource
    private InStorageWorkMapper inStorageWorkMapper;

    @Resource
    private IInStorageWorkPartService inStorageWorkPartService;

    @Resource
    private IPurchaseOrderService purchaseOrderService;

    @Override
    public void inStorageByStorageId(String id) throws Exception {
        InStorageWork storageWork = inStorageWorkMapper.findInStorageWorkById(id);
        if (null == storageWork) {
            throw new Exception("查询不到对应的入库单！【" + id + "】");
        }
        if (storageWork.getStatus() > StorageStatusEnum.SUCCESSRECEIVING.getCode()) {
            throw new Exception("该入库单已完全入库！【" + id + "】");
        }
        /**
         * 1.入库/修改入库单入库数量，采购单入库数量;库存
         * 2.修改入库单状态
         * 3.修改采购单状态
         */
        inStorageWorkPartService.inStorage(storageWork.getInStorageWorkParts());
        updateInStorageWorkStatus(storageWork);
        purchaseOrderService.updatePurchaseOrderStatus(storageWork.getFromOrder());
    }

    /**
     * 修改入库单状态
     *
     * @param storageWork
     * @throws Exception
     */
    @Override
    public void updateInStorageWorkStatus(InStorageWork storageWork) throws Exception {
        Integer successReceivingCount = 0;
        Integer partReceivingCount = 0;
        Integer receivingCode = null;
        List<InStorageWorkPart> inStorageWorkParts = storageWork.getInStorageWorkParts();
        for (InStorageWorkPart part : inStorageWorkParts) {
            if (part.getReceivingAmount().compareTo(part.getPlanAmount()) >= 0) {
                // 收货 大于采购数量的记录数
                // 如果该记录数等于明细数量则表名完全下推，否则部分下推
                successReceivingCount = successReceivingCount + 1;
            }
            if (part.getReceivingAmount().compareTo(BigDecimal.ZERO) > 0) {
                partReceivingCount = partReceivingCount + 1;
            }
        }
        if (successReceivingCount == inStorageWorkParts.size()) {
            // 完全下推的数量等于明细数量：完全下推
            receivingCode = StorageStatusEnum.SUCCESSRECEIVING.getCode();
        } else if (partReceivingCount > 0) {
            // 部分下推的数量大于明细数量：部分下推
            receivingCode = StorageStatusEnum.PARTRECEIVING.getCode();
        } else {
            receivingCode = 0;
        }
        // 如果下推状态等于零则表示错误调用不做处理
        if (receivingCode != 0) {
            inStorageWorkMapper.updateInStorageWorkStatusById(storageWork.getId(), receivingCode);
        }
    }

    @Override
    public void buidInStorageWordkByPurchaseOrder(PurchaseOrder purchaseOrder) throws Exception {
        InStorageWork storageWork = new InStorageWork();
        String id = CommonUtil.getIdByCode(OrderTypeEnum.INSTORAGEWORK.getCode());
        storageWork.setId(id);
        storageWork.setStatus(StorageStatusEnum.AUDIT.getCode());
        storageWork.setFromOrder(purchaseOrder.getId());
        storageWork.setOrderType(OrderTypeEnum.PURCHASEORDER.getCode());
        storageWork.setOrderDate(new Date());
        storageWork.setVendorId(purchaseOrder.getVendorId());
        storageWork.setVendorName(purchaseOrder.getVendorName());
        storageWork.setWarehouseId(purchaseOrder.getWarehouseId());
        storageWork.setWarehouseName(purchaseOrder.getWarehouseName());
        storageWork.setRemark(purchaseOrder.getRemark());
        super.save(storageWork);
        // 构建明细信息
        inStorageWorkPartService.buidStorageWorkPartByPurchaseOrderPart(purchaseOrder.getPurchaseOrderParts(), id);
    }

    @Override
    public InStorageWork selectById(String id) {
        return inStorageWorkMapper.findInStorageWorkById(id);
    }
}