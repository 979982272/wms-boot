package com.tudou.crm.stock.stockTrade.mapper;

import com.tudou.crm.stock.stockTrade.entity.StockTrade;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StockTradeMapper extends Mapper<StockTrade> {
    /**
     * 通过仓库编码与产品编码查询库存交易日志
     *
     * @param warehouseId
     * @param goodsId
     * @return
     */
    List<StockTrade> findStockTradeByWarehouseIdAndGoodsId(@Param("warehouseId") String warehouseId, @Param("goodsId") String goodsId);
}