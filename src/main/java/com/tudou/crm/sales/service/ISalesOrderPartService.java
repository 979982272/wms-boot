package com.tudou.crm.sales.service;

import com.tudou.crm.sales.entity.SalesOrderPart;
import com.tudou.system.platform.base.service.IBaseService;

import java.util.List;

public interface ISalesOrderPartService extends IBaseService<SalesOrderPart, String> {

    /**
     * 保存销售订单明细
     *
     * @param salesOrderParts
     * @param id
     * @throws Exception
     */
    void saveSalesOrderPart(List<SalesOrderPart> salesOrderParts, String id) throws Exception;
}
