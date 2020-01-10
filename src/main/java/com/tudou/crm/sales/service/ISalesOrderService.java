package com.tudou.crm.sales.service;

import com.tudou.crm.sales.entity.SalesOrder;
import com.tudou.system.platform.base.service.IBaseService;

public interface ISalesOrderService extends IBaseService<SalesOrder, String> {

    /**
     * 审核
     *
     * @param id
     * @throws Exception
     */
    void auditById(String id) throws Exception;

    /**
     * 更新销售单状态
     *
     * @param fromOrder
     * @throws Exception
     */
    void updateSalesOrderStatus(String fromOrder) throws Exception;
}
