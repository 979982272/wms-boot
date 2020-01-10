package com.tudou.crm.storage.outStorage.service;

import com.tudou.crm.sales.entity.SalesOrder;
import com.tudou.crm.storage.outStorage.entity.OutStorageWork;
import com.tudou.system.platform.base.service.IBaseService;

public interface IOutStorageWorkService extends IBaseService<OutStorageWork,String> {

    /**
     * 创建出库单
     * @param salesOrder
     * @throws Exception
     */
    void buidOutStorageWorkBySalesOrder(SalesOrder salesOrder)throws Exception;

    /**
     * 整单出库
     * @param id
     * @throws Exception
     */
    void outStorageByStorageId(String id)throws Exception;

    /**
     * 修改出库单状态
     * @param storageWork
     * @throws Exception
     */
    public void updateOutStorageWorkStatus(OutStorageWork storageWork) throws Exception;
}
