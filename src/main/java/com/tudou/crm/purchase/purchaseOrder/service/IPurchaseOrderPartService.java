package com.tudou.crm.purchase.purchaseOrder.service;

import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrderPart;
import com.tudou.system.platform.base.service.IBaseService;

import java.util.List;

public interface IPurchaseOrderPartService extends IBaseService<PurchaseOrderPart, String> {


    void savePurchaseApplyPart(List<PurchaseOrderPart> purchaseOrderParts, String id)throws Exception;
}
