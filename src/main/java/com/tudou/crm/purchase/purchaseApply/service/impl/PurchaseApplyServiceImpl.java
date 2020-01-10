package com.tudou.crm.purchase.purchaseApply.service.impl;

import com.tudou.crm.purchase.purchaseApply.entity.PurchaseApply;
import com.tudou.crm.purchase.purchaseApply.mapper.PurchaseApplyMapper;
import com.tudou.crm.purchase.purchaseApply.service.IPurchaseApplyPartService;
import com.tudou.crm.purchase.purchaseApply.service.IPurchaseApplyService;
import com.tudou.extra.enums.purchase.PurchaseApplyStatusEnum;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class)
@Service("purchaseApplyService")
public class PurchaseApplyServiceImpl extends BaseServiceImpl<PurchaseApply, String> implements IPurchaseApplyService {

    @Resource
    @BaseResource
    private PurchaseApplyMapper purchaseApplyMapper;

    @Resource
    private IPurchaseApplyPartService purchaseApplyPartService;

    @Override
    public void auditById(String id) throws Exception {
        PurchaseApply purchaseApply = purchaseApplyMapper.findPurchaseApplyById(id);
        if (null == purchaseApply) {
            throw new Exception("查询不到对应的采购申请单！【" + id + "】");
        }
        if (PurchaseApplyStatusEnum.CREATE.getCode() != purchaseApply.getStatus()) {
            throw new Exception("制单状态下才能进行审核操作！【" + id + "】");
        }
        purchaseApply.setStatus(PurchaseApplyStatusEnum.AUDIT.getCode());
        super.update(purchaseApply);
    }

    @Override
    public void cancelAuditById(String id) throws Exception {
        PurchaseApply purchaseApply = purchaseApplyMapper.findPurchaseApplyById(id);
        if (null == purchaseApply) {
            throw new Exception("查询不到对应的采购申请单！【" + id + "】");
        }
        if (PurchaseApplyStatusEnum.AUDIT.getCode() != purchaseApply.getStatus()) {
            throw new Exception("审核状态下才能进行取消审核操作！【" + id + "】");
        }
        purchaseApply.setStatus(PurchaseApplyStatusEnum.CREATE.getCode());
        super.update(purchaseApply);
    }

    @Override
    public PurchaseApply savePurchaseApply(PurchaseApply t) throws Exception {
        if (CollectionUtils.isEmpty(t.getPurchaseApplyParts())) {
            throw new Exception("请添加产品信息！");
        }
        t.setStatus(PurchaseApplyStatusEnum.CREATE.getCode());
        super.save(t);
        purchaseApplyPartService.savePurchaseApplyPart(t.getPurchaseApplyParts(), t.getId());
        return t;
    }

}