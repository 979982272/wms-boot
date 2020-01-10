package com.tudou.crm.purchase.purchaseOrder.service;

import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrder;
import com.tudou.system.platform.base.service.IBaseService;

public interface IPurchaseOrderService extends IBaseService<PurchaseOrder, String> {
    /**
     * 通过采购申请单组建采购订单数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    PurchaseOrder buidPurchaseOrderByApplyId(String id) throws Exception;

    /**
     * 通过下推采购申请保存采购订单
     *
     * @param purchaseOrder
     * @throws Exception
     */
    void savePurchaseOrderByApplyPush(PurchaseOrder purchaseOrder) throws Exception;

    /**
     * 保存采购订单
     *
     * @param t
     * @return
     * @throws Exception
     */
    PurchaseOrder savePurchaseOrder(PurchaseOrder t) throws Exception;

    /**
     * 审核操作
     *
     * @param id
     * @throws Exception
     */
    void auditById(String id) throws Exception;

    /**
     * 修改采购订单状态
     *
     * @param fromOrder
     * @throws Exception
     */
    void updatePurchaseOrderStatus(String fromOrder) throws Exception;
}
