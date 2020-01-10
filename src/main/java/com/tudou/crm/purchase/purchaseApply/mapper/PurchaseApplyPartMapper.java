package com.tudou.crm.purchase.purchaseApply.mapper;

import com.tudou.crm.purchase.purchaseApply.entity.PurchaseApplyPart;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PurchaseApplyPartMapper extends Mapper<PurchaseApplyPart> {
    List<PurchaseApplyPart> findPurchaseApplyPartByApplyId(String id);
}