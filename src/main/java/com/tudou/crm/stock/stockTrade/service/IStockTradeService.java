package com.tudou.crm.stock.stockTrade.service;

import com.tudou.crm.stock.stockBegin.entity.StockBegin;
import com.tudou.crm.stock.stockTrade.entity.StockTrade;
import com.tudou.crm.storage.inStorage.entity.InStorageWork;
import com.tudou.crm.storage.inStorage.entity.InStorageWorkPart;
import com.tudou.crm.storage.outStorage.entity.OutStorageWork;
import com.tudou.crm.storage.outStorage.entity.OutStorageWorkPart;
import com.tudou.system.platform.base.service.IBaseService;

import java.math.BigDecimal;

public interface IStockTradeService extends IBaseService<StockTrade, String> {
    /**
     * 通过库存期初保存库存交易日志
     *
     * @param stockBegin
     * @throws Exception
     */
    void saveStockTradeByStockBegin(StockBegin stockBegin) throws Exception;

    /**
     * 通过入库单进行保存库存交易日志
     *
     * @param storageWork
     * @param part
     * @param receivingAmount
     * @throws Exception
     */
    void saveStockTradeByInStoragePart(InStorageWork storageWork, InStorageWorkPart part, BigDecimal receivingAmount) throws Exception;

    /**
     * 通过出库单进行保存库存交易日志
     *
     * @param outStorageWork
     * @param part
     * @param deliveryAmount
     */
    void saveStockTradeByOutStoragePart(OutStorageWork outStorageWork, OutStorageWorkPart part, BigDecimal deliveryAmount) throws Exception;
}
