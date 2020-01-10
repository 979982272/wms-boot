package com.tudou.crm.stock.stockPart.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tudou.base.goods.entity.Goods;
import com.tudou.base.goods.service.IGoodsService;
import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrderPart;
import com.tudou.crm.purchase.purchaseOrder.mapper.PurchaseOrderPartMapper;
import com.tudou.crm.sales.entity.SalesOrder;
import com.tudou.crm.sales.entity.SalesOrderPart;
import com.tudou.crm.sales.mapper.SalesOrderMapper;
import com.tudou.crm.stock.stockBegin.entity.StockBegin;
import com.tudou.crm.stock.stockPart.entity.StockPart;
import com.tudou.crm.stock.stockPart.mapper.StockPartMapper;
import com.tudou.crm.stock.stockPart.service.IStockPartService;
import com.tudou.crm.stock.stockTrade.entity.StockTrade;
import com.tudou.crm.stock.stockTrade.mapper.StockTradeMapper;
import com.tudou.crm.storage.inStorage.entity.InStorageWork;
import com.tudou.crm.storage.inStorage.entity.InStorageWorkPart;
import com.tudou.crm.storage.outStorage.entity.OutStorageWork;
import com.tudou.crm.storage.outStorage.entity.OutStorageWorkPart;
import com.tudou.extra.enums.ServerFlag;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import com.tudou.util.EntityUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("stockPartService")
public class StockPartServiceImpl extends BaseServiceImpl<StockPart, String> implements IStockPartService {

    @Resource
    @BaseResource
    private StockPartMapper stockPartMapper;

    @Resource
    private StockTradeMapper stockTradeMapper;

    @Resource
    private PurchaseOrderPartMapper purchaseOrderPartMapper;

    @Resource
    private IGoodsService goodsService;

    @Resource
    private SalesOrderMapper salesOrderMapper;

    @Override
    public Map<String, StockPart> findStockByWarehouseAndGoods(String warehouseId, List<String> goodsIds) {
        Example example = new Example(StockPart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("warehouseId", warehouseId);
        criteria.andIn("goodsId", goodsIds);
        List<StockPart> stockParts = stockPartMapper.selectByExample(example);
        Map<String, StockPart> map = new HashMap<>();
        for (StockPart part : stockParts) {
            map.put(part.getGoodsId(), part);
        }
        return map;
    }

    @Override
    public void adjustlockedStock(SalesOrder salesOrder) throws Exception {
        if (null == salesOrder) {
            throw new Exception("销售订单信息不能为空！");
        }
        List<SalesOrderPart> salesOrderParts = salesOrder.getSalesOrderParts();
        if (CollectionUtils.isEmpty(salesOrderParts)) {
            throw new Exception("销售单明细不能为空！");
        }
        List<String> goodsList = new ArrayList<>();
        for (SalesOrderPart part : salesOrderParts) {
            goodsList.add(part.getGoodsId());
        }
        Map<String, StockPart> stockPartMap = findStockByWarehouseAndGoods(salesOrder.getWarehouseId(), goodsList);
        for (SalesOrderPart part : salesOrderParts) {
            StockPart stockPart = stockPartMap.get(part.getGoodsId());
            if (null == stockPart) {
                throw new Exception("查询不到对应的产品库存！产品:" + part.getGoodsName() + ";仓库:" + salesOrder.getWarehouseName());
            }
            if (part.getSalesAmount().compareTo(stockPart.getStockAmount()) > 0) {
                throw new Exception("库存不足！产品:" + part.getGoodsName() + ";仓库:" + salesOrder.getWarehouseName() + ";现有库存:" + stockPart.getStockAmount());
            }
            // 锁定库存增加/可用库存减少
            stockPart.setLockStockAmount(stockPart.getLockStockAmount().add(part.getSalesAmount()));
            stockPart.setStockAmount(stockPart.getStockAmount().subtract(part.getSalesAmount()));
            super.update(stockPart);
        }
    }

    @Override
    public Map<String, BigDecimal> findAllGoodsStock() throws Exception {
        Map<String, BigDecimal> stockMap = new HashMap<>();
        for (StockPart part : selectAll()) {
            stockMap.put(part.getGoodsId() + part.getWarehouseId(), part.getStockAmount());
        }
        return stockMap;
    }

    @Override
    public DataSourceResult loadSelectGoodsData(DataSourceRequest dataSourceRequest, String warehouseId) throws Exception {
        Example example = new Example(StockPart.class);
        Page<StockPart> page = PageHelper.startPage(dataSourceRequest.getPage(), dataSourceRequest.getPageSize());
        // 将实体的属性与数据库属性进行映射
        EntityUtil.buidSqlByRequest(dataSourceRequest, example);
        // 执行查询之后会自动把值设置到page中
        stockPartMapper.selectByExample(example);
        Map<String, Goods> goodsMap = goodsService.findAllGoodsMap();
        for (StockPart part : page.getResult()) {
            part.setGoods(goodsMap.get(part.getGoodsId()));
        }
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(page);
        dataSourceResult.setTotal(page.getTotal());
        return dataSourceResult;
    }

    @Override
    public void saveStockPartByOutStoragePart(OutStorageWork storageWork, OutStorageWorkPart part, BigDecimal deliveryAmount) throws Exception {
        StockPart stockPart = stockPartMapper.findStockPartByWarehouseIdAndGoodsId(storageWork.getWarehouseId(), part.getGoodsId());
        if (null == stockPart) {
            throw new Exception("查询不到库存！仓库:" + storageWork.getWarehouseName() + ";产品:" + part.getGoodsName());
        }
        if (deliveryAmount.compareTo(stockPart.getLockStockAmount()) > 0) {
            throw new Exception("锁定库存不足！现有库存:" + stockPart.getLockStockAmount());
        }
        // 扣减锁定库存
        stockPart.setLockStockAmount(stockPart.getLockStockAmount().subtract(deliveryAmount));
        // 扣减总库存
        stockPart.setTotalStockAmount(stockPart.getTotalStockAmount().subtract(deliveryAmount));
        super.update(stockPart);
    }

    @Override
    @Deprecated
    public void saveStockPartByInStoragePart(InStorageWork storageWork, InStorageWorkPart part, BigDecimal receivingAmount) throws Exception {
        StockPart stockPart = stockPartMapper.findStockPartByWarehouseIdAndGoodsId(storageWork.getWarehouseId(), part.getGoodsId());
        PurchaseOrderPart orderPart = purchaseOrderPartMapper.selectByPrimaryKey(part.getFromOrderPart());
        if (null == stockPart) {
            stockPart = saveStockByStoragePart(storageWork, part, orderPart, receivingAmount);
        } else {
            // 设置可用库存数/总库存数
            stockPart.setStockAmount(stockPart.getStockAmount().add(receivingAmount));
            stockPart.setTotalStockAmount(stockPart.getTotalStockAmount().add(receivingAmount));
            // 获取上次一次的库存交易成本
            BigDecimal oldUnitCostPrice = stockPart.getUnitCostPrice();
            BigDecimal oldUnitCostPriceRate = stockPart.getUnitCostPriceRate();
            // 计算新的库存成本
            BigDecimal newUnitCostPrice = (orderPart.getUnitPrice().add(oldUnitCostPrice)).divide(new BigDecimal("2"));
            // 计算新的含税单位成本
            BigDecimal newUnitCostPriceRate = (orderPart.getUnitPriceRate().add(oldUnitCostPriceRate)).divide(new BigDecimal("2"));
            stockPart.setUnitCostPrice(newUnitCostPrice);
            stockPart.setUnitCostPriceRate(newUnitCostPriceRate);
            // 设置总成本
            stockPart.setTotalCostPrice(stockPart.getTotalStockAmount().multiply(stockPart.getUnitCostPrice()));
            // 设置含税总成本
            stockPart.setTotalCostPriceRate(stockPart.getTotalStockAmount().multiply(stockPart.getUnitCostPriceRate()));
            super.update(stockPart);
        }
    }

    /**
     * 通过入库单生成库存(待重构)
     *
     * @param storageWork
     * @param part
     * @param receivingAmount
     * @return
     */
    private StockPart saveStockByStoragePart(InStorageWork storageWork, InStorageWorkPart part, PurchaseOrderPart orderPart, BigDecimal receivingAmount) throws Exception {
        StockPart stockPart = new StockPart();
        stockPart.setWarehouseId(storageWork.getWarehouseId());
        stockPart.setWarehouseName(storageWork.getWarehouseName());
        stockPart.setGoodsId(part.getGoodsId());
        stockPart.setGoodsName(part.getGoodsName());
        stockPart.setGoodsUnitId(part.getGoodsUnitId());
        stockPart.setGoodsUnitName(part.getGoodsUnitName());
        stockPart.setGoodsModel(part.getGoodsModel());
        stockPart.setTotalStockAmount(receivingAmount);
        stockPart.setStockAmount(receivingAmount);
        stockPart.setInStockAmount(BigDecimal.ZERO);
        stockPart.setLockStockAmount(BigDecimal.ZERO);
        stockPart.setUnitCostPrice(orderPart.getUnitPrice());
        stockPart.setUnitCostPriceRate(orderPart.getUnitPriceRate());
        stockPart.setTotalCostPrice(orderPart.getTotalPrice());
        stockPart.setTotalCostPriceRate(orderPart.getTotalPriceRate());
        stockPart.setServerFlag(ServerFlag.NORMAL.getCode());
        super.save(stockPart);
        return stockPart;
    }

    /**
     * 待重构
     *
     * @param stockBegin
     * @throws Exception
     */
    @Deprecated
    @Override
    public void saveStockPartByStockBegin(StockBegin stockBegin) throws Exception {
        StockPart stockPart = stockPartMapper.findStockPartByWarehouseIdAndGoodsId(stockBegin.getWarehouseId(), stockBegin.getGoodsId());
        if (null == stockPart) {
            stockPart = saveStock(stockBegin);
        } else {
            List<StockTrade> stockTrades = stockTradeMapper.findStockTradeByWarehouseIdAndGoodsId(stockBegin.getWarehouseId(), stockBegin.getGoodsId());
            // 查询到库存交易，则使用移动平均加权计算库存成本
            // 存在库存必定存在库存交易日志
            if (CollectionUtils.isEmpty(stockTrades)) {
                throw new Exception("查询不到对应的库存交易日志，请联系管理员！");
            }
            // 设置可用库存数/总库存数
            stockPart.setStockAmount(stockPart.getStockAmount().add(stockBegin.getStockAmount()));
            stockPart.setTotalStockAmount(stockPart.getTotalStockAmount().add(stockBegin.getStockAmount()));
            // 获取上次一次的库存交易成本
            StockTrade stockTrade = stockTrades.get(0);
            BigDecimal oldUnitCostPrice = stockPart.getUnitCostPrice();
            BigDecimal oldUnitCostPriceRate = stockPart.getUnitCostPriceRate();
            // 计算新的库存成本
            BigDecimal newUnitCostPrice = (stockBegin.getUnitPrice().add(oldUnitCostPrice)).divide(new BigDecimal("2"));
            // 计算新的含税单位成本
            BigDecimal newUnitCostPriceRate = (stockBegin.getUnitPriceRate().add(oldUnitCostPriceRate)).divide(new BigDecimal("2"));
            stockPart.setUnitCostPrice(newUnitCostPrice);
            stockPart.setUnitCostPriceRate(newUnitCostPriceRate);
            // 设置总成本
            stockPart.setTotalCostPrice(stockPart.getTotalStockAmount().multiply(stockPart.getUnitCostPrice()));
            // 设置含税总成本
            stockPart.setTotalCostPriceRate(stockPart.getTotalStockAmount().multiply(stockPart.getUnitCostPriceRate()));
            super.update(stockPart);
        }
    }

    /**
     * 通过库存期初保存库存
     *
     * @param stockBegin
     * @return
     * @throws Exception
     */
    private StockPart saveStock(StockBegin stockBegin) throws Exception {
        StockPart stockPart = new StockPart();
        stockPart.setWarehouseId(stockBegin.getWarehouseId());
        stockPart.setWarehouseName(stockBegin.getWarehouseName());
        stockPart.setGoodsId(stockBegin.getGoodsId());
        stockPart.setGoodsName(stockBegin.getGoodsName());
        stockPart.setGoodsUnitId(stockBegin.getGoodsUnitId());
        stockPart.setGoodsUnitName(stockBegin.getGoodsUnitName());
        stockPart.setGoodsModel(stockBegin.getGoodsModel());
        stockPart.setTotalStockAmount(stockBegin.getStockAmount());
        stockPart.setStockAmount(stockBegin.getStockAmount());
        stockPart.setInStockAmount(BigDecimal.ZERO);
        stockPart.setLockStockAmount(BigDecimal.ZERO);
        stockPart.setUnitCostPrice(stockBegin.getUnitPrice());
        stockPart.setUnitCostPriceRate(stockBegin.getUnitPriceRate());
        stockPart.setTotalCostPrice(stockBegin.getTotalPrice());
        stockPart.setTotalCostPriceRate(stockBegin.getTotalPriceRate());
        stockPart.setServerFlag(ServerFlag.NORMAL.getCode());
        super.save(stockPart);
        return stockPart;
    }


}