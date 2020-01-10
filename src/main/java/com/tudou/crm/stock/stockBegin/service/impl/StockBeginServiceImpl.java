package com.tudou.crm.stock.stockBegin.service.impl;

import com.tudou.base.goods.entity.Goods;
import com.tudou.base.goods.mapper.GoodsMapper;
import com.tudou.base.warehouse.mapper.WarehouseMapper;
import com.tudou.crm.stock.stockBegin.entity.StockBegin;
import com.tudou.crm.stock.stockBegin.mapper.StockBeginMapper;
import com.tudou.crm.stock.stockBegin.service.IStockBeginService;
import com.tudou.crm.stock.stockPart.service.IStockPartService;
import com.tudou.crm.stock.stockTrade.service.IStockTradeService;
import com.tudou.extra.enums.OrderStatusEnum;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import com.tudou.util.ArrayUtil;
import com.tudou.util.CommonUtil;
import com.tudou.util.EntityUtil;
import com.tudou.util.MathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("stockBeginService")
public class StockBeginServiceImpl extends BaseServiceImpl<StockBegin, String> implements IStockBeginService {

    @Resource
    @BaseResource
    private StockBeginMapper stockBeginMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private WarehouseMapper warehouseMapper;

    @Resource
    private IStockPartService stockPartService;

    @Resource
    private IStockTradeService stockTradeService;

    @Override
    public void deleteById(String id) throws Exception {
        StockBegin stockBegin = selectById(id);
        if (null == stockBegin) {
            throw new Exception("查询不到对应的库存期初记录！【" + id + "】");
        }
        if (stockBegin.getStatus() > OrderStatusEnum.CREATE.getCode()) {
            throw new Exception("该记录已审核完成无法进行删除操作!");
        }
        stockBeginMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void auditStockBegin(String[] ids) throws Exception {
        for (String id : ids) {
            audit(id);
        }
    }

    /**
     * 审核
     *
     * @param id
     * @throws Exception
     */
    private void audit(String id) throws Exception {
        StockBegin stockBegin = stockBeginMapper.selectByPrimaryKey(id);
        if (null == stockBegin) {
            throw new Exception("查询不到对应的期初库存记录！【" + id + "】");
        }
        stockBegin.setStatus(OrderStatusEnum.AUDIT.getCode());
        EntityUtil.setEntityAuditInfo(stockBegin);
        stockBeginMapper.updateByPrimaryKey(stockBegin);
        //1.生成库存
        stockPartService.saveStockPartByStockBegin(stockBegin);
        //2.生成库存交易日志
        stockTradeService.saveStockTradeByStockBegin(stockBegin);
    }

    @Override
    public void saveStockBegin(String[] goodsIds, BigDecimal[] goodsPrice, BigDecimal[] stockAmounts, String warehouseId) throws Exception {
        // 判断数量是否一致
        if (goodsIds.length != goodsPrice.length || goodsPrice.length != stockAmounts.length || goodsIds.length != stockAmounts.length) {
            throw new Exception("参数不一致!请联系管理员");
        }
        String warehouseName = warehouseMapper.findWarehouseNameById(warehouseId);
        if (StringUtils.isEmpty(warehouseName)) {
            throw new Exception("查询不到对应的仓库！【" + warehouseId + "】");
        }
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ArrayUtil.arrayToList(goodsIds));
        List<Goods> goodses = goodsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(goodses) || goodses.size() != goodsIds.length) {
            throw new Exception("查询不到对应的产品，或查询出来的产品数量与传递的产品参数不等!");
        }
        Map<String, Goods> goodsMap = listToMap(goodses);
        Goods goods = null;
        StockBegin stockBegin = null;
        for (int i = 0; i < goodsIds.length; i++) {
            goods = goodsMap.get(goodsIds[i]);
            stockBegin = new StockBegin();
            String id = CommonUtil.getIdByCode("SB");
            stockBegin.setId(id);
            stockBegin.setStatus(OrderStatusEnum.CREATE.getCode());
            stockBegin.setWarehouseId(warehouseId);
            stockBegin.setWarehouseName(warehouseName);
            stockBegin.setGoodsId(goods.getId());
            stockBegin.setGoodsName(goods.getGoodsName());
            stockBegin.setGoodsUnitId(goods.getGoodsUnit());
            stockBegin.setGoodsUnitName(goods.getGoodsUnitName());
            stockBegin.setGoodsModel(goods.getGoodsModel());
            stockBegin.setStockAmount(stockAmounts[i]);
            stockBegin.setUnitPrice(goodsPrice[i]);
            stockBegin.setUnitPriceRate(MathUtil.mathRate(stockBegin.getUnitPrice(), goods.getRate()));
            stockBegin.setTotalPrice(stockBegin.getStockAmount().multiply(stockBegin.getUnitPrice()));
            stockBegin.setTotalPriceRate(stockBegin.getStockAmount().multiply(stockBegin.getUnitPriceRate()));
            stockBegin.setRatePrice(stockBegin.getTotalPrice().subtract(stockBegin.getTotalPriceRate()));
            super.save(stockBegin);
        }
    }

    @Override
    public void update(StockBegin stockBegin) throws Exception {
        Goods goods = goodsMapper.selectByPrimaryKey(stockBegin.getGoodsId());
        if (null == goods) {
            throw new Exception("查询不到对应的产品信息!【" + stockBegin.getGoodsId() + "】");
        }
        stockBegin.setUnitPriceRate(MathUtil.mathRate(stockBegin.getUnitPrice(), goods.getRate()));
        stockBegin.setTotalPrice(stockBegin.getStockAmount().multiply(stockBegin.getUnitPrice()));
        stockBegin.setTotalPriceRate(stockBegin.getStockAmount().multiply(stockBegin.getUnitPriceRate()));
        stockBegin.setRatePrice(stockBegin.getTotalPrice().subtract(stockBegin.getTotalPriceRate()));
        super.update(stockBegin);
    }

    /**
     * 将集合转换为map
     *
     * @param goodses
     * @return
     */
    private Map<String, Goods> listToMap(List<Goods> goodses) {
        Map<String, Goods> map = new HashMap<>();
        for (Goods goods : goodses) {
            map.put(goods.getId(), goods);
        }
        return map;
    }
}