package com.tudou.crm.storage.outStorage.service;

import com.tudou.crm.sales.entity.SalesOrderPart;
import com.tudou.crm.storage.outStorage.entity.OutStorageWorkPart;
import com.tudou.system.platform.base.service.IBaseService;

import java.math.BigDecimal;
import java.util.List;

public interface IOutStorageWorkPartService extends IBaseService<OutStorageWorkPart, String> {

    /**
     * 创建出库单明细
     *
     * @param salesOrderParts
     * @param id
     * @throws Exception
     */
    void buidOutStoragePartWorkBySalesOrder(List<SalesOrderPart> salesOrderParts, String id) throws Exception;

    /**
     * 明细出库(整单)
     *
     * @param outStorageWorkParts
     * @throws Exception
     */
    void outStorage(List<OutStorageWorkPart> outStorageWorkParts) throws Exception;

    /**
     * 明细出库
     *
     * @param part
     * @param salesOrderPart
     * @param deliveryAmount
     * @throws Exception
     */
    void outStorage(OutStorageWorkPart part, SalesOrderPart salesOrderPart, BigDecimal deliveryAmount) throws Exception;

    /**
     * 明细出库
     * @param id
     * @param deliveryAmount
     * @throws Exception
     */
    void outStorageByPart(String id, BigDecimal deliveryAmount)throws Exception;
}
