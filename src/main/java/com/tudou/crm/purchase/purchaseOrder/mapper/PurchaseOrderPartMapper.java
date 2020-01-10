package com.tudou.crm.purchase.purchaseOrder.mapper;

import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrderPart;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PurchaseOrderPartMapper extends Mapper<PurchaseOrderPart> {
    /**
     * 通过采购订单号查询采购订单明细
     *
     * @param id
     * @return
     */
    List<PurchaseOrderPart> findPurchaseOrderPartByPurchaseId(String id);

    /**
     * 通过明细单号查询整个采购单明细
     *
     * @param id
     * @return
     */
    List<PurchaseOrderPart> findPurchaseOrderPartByPartId(String id);
}