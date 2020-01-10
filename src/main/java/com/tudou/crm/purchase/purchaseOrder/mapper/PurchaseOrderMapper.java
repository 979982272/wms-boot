package com.tudou.crm.purchase.purchaseOrder.mapper;

import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PurchaseOrderMapper extends Mapper<PurchaseOrder> {
    /**
     * 通过编码查询采购订单
     *
     * @param id
     * @return
     */
    PurchaseOrder findPurchaseOrderById(String id);

    /**
     * 修改采购订单状态
     * @param id
     * @param receivingStatus
     * @throws Exception
     */
    void updatePurchaseOrderStatusById(@Param("id") String id, @Param("receivingStatus") Integer receivingStatus)throws Exception;
}