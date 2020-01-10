package com.tudou.crm.sales.mapper;

import com.tudou.crm.sales.entity.SalesOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SalesOrderMapper extends Mapper<SalesOrder> {
    /**
     * 通过销售单号查询销售订单
     *
     * @param id
     * @return
     */
    SalesOrder findSalesOrderById(String id);

    /**
     * 修改销售单状态
     *
     * @param id
     * @param deliveryStatus
     */
    void updateSalesOrderStatusById(@Param("id") String id, @Param("deliveryStatus") Integer deliveryStatus);
}