package com.tudou.base.goods.service.impl;

import com.tudou.base.goods.entity.Goods;
import com.tudou.base.goods.mapper.GoodsMapper;
import com.tudou.base.goods.service.IGoodsService;
import com.tudou.extra.enums.ServerFlag;
import com.tudou.extra.system.AuthInfo;
import com.tudou.extra.system.UserInfoUtil;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("goodsArchiveService")
public class GoodsServiceImpl extends BaseServiceImpl<Goods, String> implements IGoodsService {

    @Resource
    @BaseResource
    private GoodsMapper goodsMapper;


    @Override
    public Map<String, Goods> findAllGoodsMap() throws Exception {
        List<Goods> goodses = goodsMapper.selectAll();
        Map<String,Goods> goodsMap = new HashMap<>();
        for (Goods goods : goodses){
            goodsMap.put(goods.getId(),goods);
        }
        return goodsMap;
    }

    @Override
    public DataSourceResult selectVendorGoods(DataSourceRequest dataSourceRequest, HttpServletRequest request, String vendorId) throws Exception {
        DataSourceResult dataSourceResult = new DataSourceResult();
        Map<String, Object> map = new HashMap<>();
        map.put("vendorId", vendorId);
        map.put("dataSourceRequest", dataSourceRequest);
        List<Goods> goods = goodsMapper.findVendorGoodsByVendorId(map);
        dataSourceResult.setData(goods);
        dataSourceResult.setTotal(goods.size());
        return dataSourceResult;
    }

    @Override
    public Goods save(Goods t) throws Exception {
        valide(t);
        if (goodsMapper.selectByPrimaryKey(t) != null) {
            throw new Exception("产品编码重复！【" + t.getId() + "】");
        }
        if (CollectionUtils.isNotEmpty(goodsMapper.findGoodsByGoodsName(t.getGoodsName()))) {
            throw new Exception("产品名称重复！【" + t.getGoodsName() + "】");
        }
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        t.setCreateTime(new Date());
        t.setCreateEmpId(authInfo.getEmpId());
        t.setCreateEmp(authInfo.getEmpName());
        goodsMapper.insert(t);
        return t;
    }

    @Override
    public void update(Goods t) throws Exception {
        valide(t);
        if (goodsMapper.selectByPrimaryKey(t) == null) {
            throw new Exception("查询不到对应的产品档案！【" + t.getId() + "】");
        }
        if (CollectionUtils.isNotEmpty(goodsMapper.findGoodsByGoodsNameAndNotId(t.getGoodsName(), t.getId()))) {
            throw new Exception("产品名称重复！【" + t.getGoodsName() + "】");
        }
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        t.setModifyTime(new Date());
        t.setModifyEmpId(authInfo.getEmpId());
        t.setModifyEmp(authInfo.getEmpName());
        goodsMapper.updateByPrimaryKey(t);
    }

    @Override
    public void deleteById(String id) throws Exception {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        if (null == goods) {
            throw new Exception("查询不到对应的产品档案！【" + goods.getId() + "】");
        }
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        goods.setServerFlag(ServerFlag.Delete.getCode());
        goods.setDeleteTime(new Date());
        goods.setDeleteEmp(authInfo.getEmpName());
        goods.setDeleteEmpId(authInfo.getEmpId());
        goodsMapper.updateByPrimaryKey(goods);
    }

    private void valide(Goods goods) throws Exception {
        if (null == goods) {
            throw new Exception("产品档案信息不能为空！");
        }
        if (StringUtils.isEmpty(goods.getId())) {
            throw new Exception("产品编码不能为空！");
        }
        if (StringUtils.isEmpty(goods.getGoodsName())) {
            throw new Exception("产品名称不能为空！");
        }
        if (StringUtils.isEmpty(goods.getGoodsUnit())) {
            throw new Exception("产品类型不能为空！");
        }
        if (StringUtils.isEmpty(goods.getGoodsBrand())) {
            throw new Exception("产品品牌不能为空！");
        }
        if (StringUtils.isEmpty(goods.getGoodsUnit())) {
            throw new Exception("产品单位不能为空！");
        }
    }
}