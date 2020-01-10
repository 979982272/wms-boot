package com.tudou.crm.purchase.purchaseApply.service;

import com.tudou.crm.purchase.purchaseApply.entity.PurchaseApplyPart;
import com.tudou.system.platform.base.service.IBaseService;

import java.util.List;

public interface IPurchaseApplyPartService extends IBaseService<PurchaseApplyPart,String> {

    /**
     * 保存明细信息
     * @param purchaseApplyParts
     * @param applyId
     * @throws Exception
     */
    void savePurchaseApplyPart(List<PurchaseApplyPart> purchaseApplyParts, String applyId)throws Exception;
}
