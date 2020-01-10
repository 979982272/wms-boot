package com.tudou.crm.storage.outStorage.service.impl;

import com.tudou.crm.sales.entity.SalesOrderPart;
import com.tudou.crm.sales.mapper.SalesOrderPartMapper;
import com.tudou.crm.sales.service.ISalesOrderPartService;
import com.tudou.crm.sales.service.ISalesOrderService;
import com.tudou.crm.stock.stockPart.service.IStockPartService;
import com.tudou.crm.stock.stockTrade.service.IStockTradeService;
import com.tudou.crm.storage.outStorage.entity.OutStorageWork;
import com.tudou.crm.storage.outStorage.entity.OutStorageWorkPart;
import com.tudou.crm.storage.outStorage.mapper.OutStorageWorkMapper;
import com.tudou.crm.storage.outStorage.mapper.OutStorageWorkPartMapper;
import com.tudou.crm.storage.outStorage.service.IOutStorageWorkPartService;
import com.tudou.crm.storage.outStorage.service.IOutStorageWorkService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("outStorageWorkPartService")
public class OutStorageWorkPartServiceImpl extends BaseServiceImpl<OutStorageWorkPart, String> implements IOutStorageWorkPartService {

    @Resource
    @BaseResource
    private OutStorageWorkPartMapper outStorageWorkPartMapper;

    @Resource
    private SalesOrderPartMapper salesOrderPartMapper;

    @Resource
    private ISalesOrderPartService salesOrderPartService;

    @Resource
    private OutStorageWorkMapper outStorageWorkMapper;

    @Resource
    private IStockPartService stockPartService;

    @Resource
    private IStockTradeService stockTradeService;

    @Resource
    private IOutStorageWorkService outStorageWorkService;

    @Resource
    private ISalesOrderService salesOrderService;

    @Override
    public void outStorageByPart(String id, BigDecimal deliveryAmount) throws Exception {
        OutStorageWorkPart outStorageWorkPart = selectById(id);
        if (null == outStorageWorkPart) {
            throw new Exception("查询不到对应的明细信息!【" + id + "】");
        }
        SalesOrderPart salesOrderPart = salesOrderPartService.selectById(outStorageWorkPart.getFromOrderPart());
        /**
         * 一、 1.修改出库单出库数量
         *      2.修改对应的销售单明细的出库数量
         *      3.生成库存
         *      4.生成交易日志
         * 二、 1.修改出库单状态
         * 三、 1.修改销售单状态
         */
        outStorage(outStorageWorkPart, salesOrderPart, deliveryAmount);
        OutStorageWork outStorageWork = outStorageWorkMapper.findOutStorageWorkById(outStorageWorkPart.getOutStorageWork());
        outStorageWorkService.updateOutStorageWorkStatus(outStorageWork);
        salesOrderService.updateSalesOrderStatus(outStorageWork.getFromOrder());
    }

    @Override
    public void outStorage(List<OutStorageWorkPart> outStorageWorkParts) throws Exception {
        if (CollectionUtils.isEmpty(outStorageWorkParts)) {
            throw new Exception("出库单明细不能为空!");
        }
        List<SalesOrderPart> salesOrderParts = salesOrderPartMapper.findSalesOrderPartBySalesOrderPartId(outStorageWorkParts.get(0).getFromOrderPart());
        if (CollectionUtils.isEmpty(salesOrderParts)) {
            throw new Exception("对应销售单明细不能为空!");
        }
        if (salesOrderParts.size() != outStorageWorkParts.size()) {
            throw new Exception("对应的销售单明细与出库单明细数量不相等！");
        }
        Map<String, SalesOrderPart> salesOrderPartMap = new HashMap<>();
        for (SalesOrderPart part : salesOrderParts) {
            salesOrderPartMap.put(part.getId(), part);
        }
        for (OutStorageWorkPart part : outStorageWorkParts) {
            outStorage(part, salesOrderPartMap.get(part.getFromOrderPart()), null);
        }
    }

    @Override
    public void outStorage(OutStorageWorkPart part, SalesOrderPart salesOrderPart, BigDecimal deliveryAmount) throws Exception {
        if (null == part) {
            throw new Exception("出库明细为空！");
        }
        if (null == salesOrderPart) {
            throw new Exception("销售单明细为空！");
        }
        /**
         * 1.修改出库单出库数量
         * 2.修改对应的销售单明细出库数量
         * 3.扣减库存；总库存，锁定库存
         * 4.生成交易日志
         */
        if (deliveryAmount == null || deliveryAmount.compareTo(BigDecimal.ZERO) == 0) {
            deliveryAmount = part.getPlanAmount().subtract(part.getDeliveryAmount());
        }
        part.setDeliveryAmount(part.getDeliveryAmount().add(deliveryAmount));
        super.update(part);
        salesOrderPart.setDeliveryAmount(salesOrderPart.getDeliveryAmount().add(deliveryAmount));
        salesOrderPartService.update(salesOrderPart);
        // 查询主单
        OutStorageWork outStorageWork = outStorageWorkMapper.selectByPrimaryKey(part.getOutStorageWork());
        stockPartService.saveStockPartByOutStoragePart(outStorageWork, part, deliveryAmount);
        stockTradeService.saveStockTradeByOutStoragePart(outStorageWork, part, deliveryAmount);
    }

    @Override
    public void buidOutStoragePartWorkBySalesOrder(List<SalesOrderPart> salesOrderParts, String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            throw new Exception("出库单号不能为空！");
        }
        if (CollectionUtils.isEmpty(salesOrderParts)) {
            throw new Exception("销售单明细不能为空！");
        }
        OutStorageWorkPart outStorageWorkPart = null;
        for (SalesOrderPart part : salesOrderParts) {
            outStorageWorkPart = new OutStorageWorkPart();
            outStorageWorkPart.setFromOrderPart(part.getId());
            outStorageWorkPart.setOutStorageWork(id);
            outStorageWorkPart.setGoodsId(part.getGoodsId());
            outStorageWorkPart.setGoodsName(part.getGoodsName());
            outStorageWorkPart.setGoodsUnitId(part.getGoodsUnitId());
            outStorageWorkPart.setGoodsUnitName(part.getGoodsUnitName());
            outStorageWorkPart.setGoodsModel(part.getGoodsModel());
            outStorageWorkPart.setPlanAmount(part.getSalesAmount());
            outStorageWorkPart.setDeliveryAmount(BigDecimal.ZERO);
            outStorageWorkPart.setRemark(part.getRemark());
            super.save(outStorageWorkPart);
        }
    }
}