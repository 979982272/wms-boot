package com.tudou.crm.stock.stockPart.service;

import com.tudou.crm.sales.entity.SalesOrder;
import com.tudou.crm.stock.stockBegin.entity.StockBegin;
import com.tudou.crm.stock.stockPart.entity.StockPart;
import com.tudou.crm.storage.inStorage.entity.InStorageWork;
import com.tudou.crm.storage.inStorage.entity.InStorageWorkPart;
import com.tudou.crm.storage.outStorage.entity.OutStorageWork;
import com.tudou.crm.storage.outStorage.entity.OutStorageWorkPart;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.service.IBaseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IStockPartService extends IBaseService<StockPart, String> {
    /**
     * 通过库存期初生成库存
     *
     * @param stockBegin
     */
    void saveStockPartByStockBegin(StockBegin stockBegin) throws Exception;

    /**
     * 通过入库单生成库存
     *
     * @param storageWork
     * @param part
     * @param receivingAmount
     * @throws Exception
     */
    void saveStockPartByInStoragePart(InStorageWork storageWork, InStorageWorkPart part, BigDecimal receivingAmount) throws Exception;

    /**
     * 通过出库单生成库存
     * @param storageWork
     * @param part
     * @param deliveryAmount
     * @throws Exception
     */
    void saveStockPartByOutStoragePart(OutStorageWork storageWork, OutStorageWorkPart part, BigDecimal deliveryAmount) throws Exception;

    /**
     * 选择产品By库存
     *
     * @param dataSourceRequest
     * @param warehouseId
     * @return
     * @throws Exception
     */
    DataSourceResult loadSelectGoodsData(DataSourceRequest dataSourceRequest, String warehouseId) throws Exception;

    /**
     * 查找所有产品的库存
     *
     * @return
     * @throws Exception
     */
    Map<String, BigDecimal> findAllGoodsStock() throws Exception;

    /**
     * 将可用库存调整到锁定库存
     * @param salesOrder
     * @throws Exception
     */
    void adjustlockedStock(SalesOrder salesOrder)throws Exception;

    /**
     * 通过仓库和产品编码查询所有的库存
     * @param warehouseId
     * @param goodsIds
     * @return
     */
    Map<String,StockPart> findStockByWarehouseAndGoods(String warehouseId, List<String> goodsIds);
}
