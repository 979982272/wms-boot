package com.tudou.base.warehouse.mapper;

import com.tudou.base.warehouse.entity.Warehouse;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface WarehouseMapper extends Mapper<Warehouse> {
    /**
     * 通过仓库编码查询仓库名称
     *
     * @param warehouseId
     * @return
     */
    @Select("select warehouse_name from eidp_warehouse where id = #{warehouseId}")
    // 需要修改
    //@Result(column = "warehouse_name", property = "warehouseName")
    String findWarehouseNameById(String warehouseId);
}