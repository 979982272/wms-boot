package com.tudou.crm.storage.inStorage.service;

import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrder;
import com.tudou.crm.storage.inStorage.entity.InStorageWork;
import com.tudou.system.platform.base.service.IBaseService;

public interface IInStorageWorkService extends IBaseService<InStorageWork,String> {

    /**
     * 通过采购订单生成入库作业单
     * @param purchaseOrder
     * @throws Exception
     */
    void buidInStorageWordkByPurchaseOrder(PurchaseOrder purchaseOrder)throws Exception;

    /**
     * 通过入库单号入库
     * @param id
     * @throws Exception
     */
    void inStorageByStorageId(String id)throws Exception;

    public void updateInStorageWorkStatus(InStorageWork storageWork) throws Exception;
}
