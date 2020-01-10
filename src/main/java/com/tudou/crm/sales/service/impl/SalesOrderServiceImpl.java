package com.tudou.crm.sales.service.impl;

import com.tudou.crm.sales.entity.SalesOrder;
import com.tudou.crm.sales.entity.SalesOrderPart;
import com.tudou.crm.sales.mapper.SalesOrderMapper;
import com.tudou.crm.sales.service.ISalesOrderPartService;
import com.tudou.crm.sales.service.ISalesOrderService;
import com.tudou.crm.stock.stockPart.service.IStockPartService;
import com.tudou.crm.storage.outStorage.service.IOutStorageWorkService;
import com.tudou.extra.enums.sales.SalesOrderStatusEnum;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import com.tudou.util.EntityUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("salesOrderService")
public class SalesOrderServiceImpl extends BaseServiceImpl<SalesOrder, String> implements ISalesOrderService {

    @Resource
    @BaseResource
    private SalesOrderMapper salesOrderMapper;

    @Resource
    private ISalesOrderPartService salesOrderPartService;

    @Resource
    private IOutStorageWorkService outStorageWorkService;

    @Resource
    private IStockPartService stockPartService;

    @Override
    public void updateSalesOrderStatus(String fromOrder) throws Exception {
        Integer successDeliveryCount = 0;
        Integer partDeliveryCount = 0;
        Integer deliveryStatus = null;
        SalesOrder salesOrder = salesOrderMapper.findSalesOrderById(fromOrder);
        List<SalesOrderPart> salesOrderParts = salesOrder.getSalesOrderParts();
        for (SalesOrderPart part : salesOrderParts) {
            if (part.getDeliveryAmount().compareTo(part.getSalesAmount()) >= 0) {
                // 下推数量 大于申请数量的记录数
                // 如果改记录数等于明细数量则表名完全下推，否则部分下推
                successDeliveryCount = successDeliveryCount + 1;
            }
            if (part.getDeliveryAmount().compareTo(BigDecimal.ZERO) > 0) {
                partDeliveryCount = partDeliveryCount + 1;
            }
        }
        if (successDeliveryCount == salesOrderParts.size()) {
            // 完全下推的数量等于明细数量：完全下推
            deliveryStatus = SalesOrderStatusEnum.SUCCESSDELIVERY.getCode();
        } else if (partDeliveryCount > 0) {
            // 部分下推的数量大于明细数量：部分下推
            deliveryStatus = SalesOrderStatusEnum.PARTDELIVERY.getCode();
        } else {
            deliveryStatus = 0;
        }
        // 如果下推状态等于零则表示错误调用不做处理
        if (deliveryStatus != 0) {
            salesOrderMapper.updateSalesOrderStatusById(fromOrder, deliveryStatus);
        }
    }

    @Override
    public void auditById(String id) throws Exception {
        SalesOrder salesOrder = salesOrderMapper.findSalesOrderById(id);
        if (null == salesOrder) {
            throw new Exception("查询不到对应的销售订单！【" + id + "】");
        }
        salesOrder.setStatus(SalesOrderStatusEnum.AUDIT.getCode());
        EntityUtil.setEntityAuditInfo(salesOrder);
        super.update(salesOrder);
        // 构建出库单
        outStorageWorkService.buidOutStorageWorkBySalesOrder(salesOrder);
        // 将可用库存调整到锁定库存
        stockPartService.adjustlockedStock(salesOrder);
    }

    @Override
    public SalesOrder save(SalesOrder salesOrder) throws Exception {
        if (CollectionUtils.isEmpty(salesOrder.getSalesOrderParts())) {
            throw new Exception("请添加产品信息！");
        }
        super.save(salesOrder);
        salesOrderPartService.saveSalesOrderPart(salesOrder.getSalesOrderParts(), salesOrder.getId());
        return salesOrder;
    }
}