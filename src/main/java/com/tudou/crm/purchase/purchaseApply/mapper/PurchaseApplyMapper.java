package com.tudou.crm.purchase.purchaseApply.mapper;

import com.tudou.crm.purchase.purchaseApply.entity.PurchaseApply;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PurchaseApplyMapper extends Mapper<PurchaseApply> {
    /**
     * 查询采购申请单，一对多
     *
     * @param id
     * @return
     */
    PurchaseApply findPurchaseApplyById(String id);

    /**
     * 修改采购申请状态
     *
     * @param id
     * @param status
     */
    void updatePurchaseApplyStatusById(@Param("id") String id, @Param("status") Integer status);
}