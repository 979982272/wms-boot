package com.tudou.crm.stock.stockPart.mapper;

import com.tudou.crm.stock.stockPart.entity.StockPart;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface StockPartMapper extends Mapper<StockPart> {
    /**
     * 通过仓库编码和产品编码查询库存
     * @param warehouseId
     * @param goodsId
     * @return
     */
    StockPart findStockPartByWarehouseIdAndGoodsId(@Param("warehouseId") String warehouseId, @Param("goodsId") String goodsId);
}