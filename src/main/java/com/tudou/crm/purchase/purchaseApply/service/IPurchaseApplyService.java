package com.tudou.crm.purchase.purchaseApply.service;

import com.tudou.crm.purchase.purchaseApply.entity.PurchaseApply;
import com.tudou.system.platform.base.service.IBaseService;

public interface IPurchaseApplyService extends IBaseService<PurchaseApply, String> {
    /**
     * 保存
      * @param t
     * @return
     * @throws Exception
     */
    PurchaseApply savePurchaseApply(PurchaseApply t) throws Exception;

    /**
     * 审核
     * @param id
     * @throws Exception
     */
    void auditById(String id)throws Exception;

    /**
     * 取消审核
     * @param id
     * @throws Exception
     */
    void cancelAuditById(String id)throws Exception;
}
