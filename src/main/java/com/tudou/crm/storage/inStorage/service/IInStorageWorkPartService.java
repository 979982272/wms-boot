package com.tudou.crm.storage.inStorage.service;

import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrderPart;
import com.tudou.crm.storage.inStorage.entity.InStorageWorkPart;
import com.tudou.system.platform.base.service.IBaseService;

import java.math.BigDecimal;
import java.util.List;

public interface IInStorageWorkPartService extends IBaseService<InStorageWorkPart, String> {

    /**
     * 通过采购订单明细构建入库单明细
     *
     * @param purchaseOrderParts
     * @param id
     * @throws Exception
     */
    void buidStorageWorkPartByPurchaseOrderPart(List<PurchaseOrderPart> purchaseOrderParts, String id) throws Exception;

    /**
     * 入库整单
     *
     * @param inStorageWorkParts
     * @throws Exception
     */
    void inStorage(List<InStorageWorkPart> inStorageWorkParts) throws Exception;

    /**
     * 整单入库
     * @param part
     * @throws Exception
     */
    void inStorage(InStorageWorkPart part, PurchaseOrderPart purchaseOrderPart, BigDecimal receivingAmount) throws Exception;

    /**
     * 明细入库
     * @param id
     * @param receivingAmount
     * @throws Exception
     */
    void inStorageByPart(String id, BigDecimal receivingAmount)throws Exception;
}
