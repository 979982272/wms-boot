package com.tudou.crm.sales.mapper;

import com.tudou.crm.sales.entity.SalesOrderPart;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SalesOrderPartMapper extends Mapper<SalesOrderPart> {
    /**
     * 通过销售单号查询明细
     *
     * @param id
     * @return
     */
    List<SalesOrderPart> findSalesOrderPartBySalesOrderId(String id);

    /**
     * 通过明细查询整单明细
     *
     * @param id
     * @return
     */
    List<SalesOrderPart> findSalesOrderPartBySalesOrderPartId(String id);
}