package com.tudou.crm.sales.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tudou.base.goods.entity.Goods;
import com.tudou.common.service.ICommonService;
import com.tudou.crm.sales.entity.SalesOrder;
import com.tudou.crm.sales.entity.SalesOrderPart;
import com.tudou.crm.sales.mapper.SalesOrderPartMapper;
import com.tudou.crm.sales.service.ISalesOrderPartService;
import com.tudou.crm.sales.service.ISalesOrderService;
import com.tudou.crm.stock.stockPart.service.IStockPartService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import com.tudou.util.EntityUtil;
import com.tudou.util.MathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("salesOrderPartService")
public class SalesOrderPartServiceImpl extends BaseServiceImpl<SalesOrderPart, String> implements ISalesOrderPartService {

    @Resource
    @BaseResource
    private SalesOrderPartMapper salesOrderPartMapper;

    @Resource
    private IStockPartService stockPartService;

    @Resource
    private ISalesOrderService salesOrderService;

    @Resource
    private ICommonService commonService;

    @Override
    public SalesOrderPart save(SalesOrderPart t) throws Exception {
        List<SalesOrderPart> salesOrderParts = new ArrayList<>();
        salesOrderParts.add(t);
        Example example = new Example(SalesOrderPart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("goods_id = ", t.getGoodsId());
        criteria.andCondition("sales_order_id = ", t.getSalesOrderId());
        if (CollectionUtils.isNotEmpty(salesOrderPartMapper.selectByExample(example))) {
            throw new Exception("该产品在销售单中已存在！【" + t.getGoodsId() + "】");
        }
        saveSalesOrderPart(salesOrderParts, t.getSalesOrderId());
        return t;
    }

    @Override
    public void update(SalesOrderPart t) throws Exception {
        Example example = new Example(SalesOrderPart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("id != ", t.getId());
        criteria.andCondition("goods_id = ", t.getGoodsId());
        criteria.andCondition("sales_order_id = ", t.getSalesOrderId());
        if (CollectionUtils.isNotEmpty(salesOrderPartMapper.selectByExample(example))) {
            throw new Exception("该产品在销售单中已存在！【" + t.getGoodsId() + "】");
        }
        List<SalesOrderPart> salesOrderParts = new ArrayList<>();
        salesOrderParts.add(t);
        updateSalesOrderPart(salesOrderParts, t.getSalesOrderId());
    }

    public void updateSalesOrderPart(List<SalesOrderPart> salesOrderParts, String salesOrderId) throws Exception {
        Map<String, Goods> goodsMap = commonValid(salesOrderParts, salesOrderId);
        Goods goods = null;
        for (SalesOrderPart part : salesOrderParts) {
            goods = goodsMap.get(part.getGoodsId());
            if (null == goods) {
                throw new Exception("查询不到对应的产品信息！【" + part.getGoodsId() + "】");
            }
            part.setSalesOrderId(salesOrderId);
            part.setGoodsId(goods.getId());
            setData(part, goods);
            super.update(part);
        }
    }

    private Map<String, Goods> commonValid(List<SalesOrderPart> salesOrderParts, String salesOrderId) throws Exception {
        if (StringUtils.isEmpty(salesOrderId)) {
            throw new Exception("销售单号不能为空！");
        }
        if (CollectionUtils.isEmpty(salesOrderParts)) {
            throw new Exception("销售明细不能为空！");
        }
        SalesOrder salesOrder = salesOrderService.selectById(salesOrderId);
        if (null == salesOrder) {
            throw new Exception("查询不到对应的销售订单！【" + salesOrderId + "】");
        }
        List<String> goodsIds = new ArrayList<>();
        Map<String, BigDecimal> map = stockPartService.findAllGoodsStock();
        // 验证明细信息
        for (SalesOrderPart salesOrderPart : salesOrderParts) {
            if (StringUtils.isEmpty(salesOrderPart.getGoodsId())) {
                throw new Exception("产品编码为空！");
            }
            valid(salesOrderPart, salesOrder.getWarehouseId(), map);
            goodsIds.add(salesOrderPart.getGoodsId());
        }
        Map<String, Goods> goodsMap = commonService.findGoodsByIdsAndToMap(goodsIds);
        return goodsMap;
    }

    @Override
    public void saveSalesOrderPart(List<SalesOrderPart> salesOrderParts, String salesOrderId) throws Exception {
        Map<String, Goods> goodsMap = commonValid(salesOrderParts, salesOrderId);
        Goods goods = null;
        for (SalesOrderPart part : salesOrderParts) {
            goods = goodsMap.get(part.getGoodsId());
            if (null == goods) {
                throw new Exception("查询不到对应的产品信息！【" + part.getGoodsId() + "】");
            }
            part.setSalesOrderId(salesOrderId);
            part.setGoodsId(goods.getId());
            setData(part, goods);
            super.save(part);
        }
    }

    /**
     * 设置值
     *
     * @param part
     * @param goods
     * @throws Exception
     */
    private void setData(SalesOrderPart part, Goods goods) throws Exception {
        part.setGoodsUnitId(goods.getGoodsUnit());
        part.setGoodsUnitName(goods.getGoodsUnitName());
        part.setGoodsName(goods.getGoodsName());
        part.setGoodsModel(goods.getGoodsModel());
        part.setUnitPriceRate(MathUtil.mathRate(part.getUnitPrice(), goods.getRate()));
        part.setTotalPrice(part.getSalesAmount().multiply(part.getUnitPrice()));
        part.setTotalPriceRate(part.getSalesAmount().multiply(part.getUnitPriceRate()));
    }

    /**
     * 验证
     *
     * @param salesOrderPart
     * @param warehouseId
     * @param map
     * @throws Exception
     */
    private void valid(SalesOrderPart salesOrderPart, String warehouseId, Map<String, BigDecimal> map) throws Exception {
        if (null == salesOrderPart.getUnitPrice() || BigDecimal.ZERO.compareTo(salesOrderPart.getUnitPrice()) >= 0) {
            throw new Exception("采购价格不能为空,并且需要大于零！");
        }
        if (null == salesOrderPart.getSalesAmount() || BigDecimal.ZERO.compareTo(salesOrderPart.getSalesAmount()) >= 0) {
            throw new Exception("申请数量不能为空,并且需要大于零！");
        }
        if (null == salesOrderPart.getRate()) {
            salesOrderPart.setRate(BigDecimal.ZERO);
        }
        BigDecimal stockAmount = map.get(salesOrderPart.getGoodsId() + warehouseId);
        stockAmount = stockAmount == null ? BigDecimal.ZERO : stockAmount;
        // 如果库存为空或者等于零或者销售数量大于库存
        // 修改为在审核时判断库存
//        if (null == stockAmount || BigDecimal.ZERO.compareTo(stockAmount) >= 0 || salesOrderPart.getSalesAmount().compareTo(stockAmount) > 0) {
//            throw new Exception("产品【" + salesOrderPart.getGoodsId() + "】库存不足！库存数量:" + stockAmount);
//        }
    }

    @Override
    public DataSourceResult loadData(Class c, DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        Example example = new Example(c);
        Page<SalesOrderPart> page = PageHelper.startPage(dataSourceRequest.getPage(), dataSourceRequest.getPageSize());
        // 将实体的属性与数据库属性进行映射
        EntityUtil.buidSqlByRequest(dataSourceRequest, example);
        Example.Criteria criteria = example.getOredCriteria().get(0);
        criteria.andCondition("delivery_amount > ", 0);
        // 执行查询之后会自动把值设置到page中
        salesOrderPartMapper.selectByExample(example);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(page);
        dataSourceResult.setTotal(page.getTotal());
        return dataSourceResult;
    }

}