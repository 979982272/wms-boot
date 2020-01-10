package com.tudou.crm.purchase.purchaseApply.service.impl;

import com.tudou.base.goods.entity.Goods;
import com.tudou.base.goods.mapper.GoodsMapper;
import com.tudou.common.service.ICommonService;
import com.tudou.crm.purchase.purchaseApply.entity.PurchaseApplyPart;
import com.tudou.crm.purchase.purchaseApply.mapper.PurchaseApplyPartMapper;
import com.tudou.crm.purchase.purchaseApply.service.IPurchaseApplyPartService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import com.tudou.util.MathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("purchaseApplyPartService")
public class PurchaseApplyPartServiceImpl extends BaseServiceImpl<PurchaseApplyPart, String> implements IPurchaseApplyPartService {

    @Resource
    @BaseResource
    private PurchaseApplyPartMapper purchaseApplyPartMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private ICommonService commonService;

    @Override
    public PurchaseApplyPart save(PurchaseApplyPart part) throws Exception {
        if (StringUtils.isEmpty(part.getPurchaseApplyId())) {
            throw new Exception("采购申请单号不能为空！");
        }
        Goods goods = goodsMapper.selectByPrimaryKey(part.getGoodsId());
        valid(part);
        if (null == goods) {
            throw new Exception("查询不到对应的产品!【" + part.getGoodsId() + "】");
        }
        setData(part, goods);
        Example example = new Example(PurchaseApplyPart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("goods_id = ", part.getGoodsId());
        criteria.andCondition("purchase_apply_id =", part.getPurchaseApplyId());
        List<PurchaseApplyPart> purchaseApplyParts = purchaseApplyPartMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(purchaseApplyParts)) {
            throw new Exception("采购申请单中已存在产品【" + part.getGoodsId() + "】");
        }
        super.save(part);
        return part;
    }

    @Override
    public void update(PurchaseApplyPart part) throws Exception {
        if (StringUtils.isEmpty(part.getPurchaseApplyId())) {
            throw new Exception("采购申请单号不能为空！");
        }
        Goods goods = goodsMapper.selectByPrimaryKey(part.getGoodsId());
        valid(part);
        if (null == goods) {
            throw new Exception("查询不到对应的产品!【" + part.getGoodsId() + "】");
        }
        setData(part, goods);
        Example example = new Example(PurchaseApplyPart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("goods_id = ", part.getGoodsId());
        criteria.andCondition("purchase_apply_id =", part.getPurchaseApplyId());
        criteria.andNotEqualTo("id", part.getId());
        List<PurchaseApplyPart> purchaseApplyParts = purchaseApplyPartMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(purchaseApplyParts)) {
            throw new Exception("采购申请单中已存在产品【" + part.getGoodsId() + "】");
        }
        super.update(part);
    }

    private void setData(PurchaseApplyPart part, Goods goods) {
        part.setGoodsUnitId(goods.getGoodsUnit());
        part.setGoodsUnitName(goods.getGoodsUnitName());
        part.setGoodsName(goods.getGoodsName());
        part.setGoodsModel(goods.getGoodsModel());
        part.setUnitPriceRate(MathUtil.mathRate(part.getUnitPrice(), part.getRate()));
        part.setTotalPrice(part.getApplyAmount().multiply(part.getUnitPrice()));
        part.setTotalPriceRate(part.getApplyAmount().multiply(part.getUnitPriceRate()));
    }

    private void valid(PurchaseApplyPart purchaseApplyPart) throws Exception {
        if (null == purchaseApplyPart.getUnitPrice() || BigDecimal.ZERO.compareTo(purchaseApplyPart.getUnitPrice()) >= 0) {
            throw new Exception("采购价格不能为空,并且需要大于零！");
        }
        if (null == purchaseApplyPart.getApplyAmount() || BigDecimal.ZERO.compareTo(purchaseApplyPart.getApplyAmount()) >= 0) {
            throw new Exception("申请数量不能为空,并且需要大于零！");
        }
        if (null == purchaseApplyPart.getRate()) {
            purchaseApplyPart.setRate(BigDecimal.ZERO);
        }
    }

    @Override
    public void savePurchaseApplyPart(List<PurchaseApplyPart> purchaseApplyParts, String purchaseApplyId) throws Exception {
        if (StringUtils.isEmpty(purchaseApplyId)) {
            throw new Exception("采购申请单号不能为空！");
        }
        if (CollectionUtils.isEmpty(purchaseApplyParts)) {
            throw new Exception("采购申请明细不能为空！");
        }
        List<String> goodsIds = new ArrayList<>();
        // 验证明细信息
        for (PurchaseApplyPart purchaseApplyPart : purchaseApplyParts) {
            if (StringUtils.isEmpty(purchaseApplyPart.getGoodsId())) {
                throw new Exception("产品编码为空！");
            }
            valid(purchaseApplyPart);
            goodsIds.add(purchaseApplyPart.getGoodsId());
        }
        Map<String, Goods> goodsMap = commonService.findGoodsByIdsAndToMap(goodsIds);
        Goods goods = null;
        for (PurchaseApplyPart part : purchaseApplyParts) {
            goods = goodsMap.get(part.getGoodsId());
            if (null == goods) {
                throw new Exception("查询不到对应的产品信息！【" + part.getGoodsId() + "】");
            }
            part.setPurchaseApplyId(purchaseApplyId);
            part.setGoodsId(goods.getId());
            setData(part, goods);
            super.save(part);
        }
    }
}