package com.tudou.crm.stock.stockBegin.service;

import com.tudou.crm.stock.stockBegin.entity.StockBegin;
import com.tudou.system.platform.base.service.IBaseService;

import java.math.BigDecimal;

public interface IStockBeginService extends IBaseService<StockBegin,String> {

    /**
     * 爆粗期初库存
     * @param goodsIds 产品编码
     * @param goodsPrice 单价
     * @param stockAmounts 期初数量
     * @param warehouseId 仓库编码
     * @throws Exception
     */
    void saveStockBegin(String[] goodsIds, BigDecimal[] goodsPrice, BigDecimal[] stockAmounts, String warehouseId)throws Exception;

    /**
     * 审核库存期初
     * @param id
     * @throws Exception
     */
    void auditStockBegin(String[] id)throws Exception;
}
