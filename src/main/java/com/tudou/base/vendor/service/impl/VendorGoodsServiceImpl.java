package com.tudou.base.vendor.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tudou.base.goods.entity.Goods;
import com.tudou.base.goods.mapper.GoodsMapper;
import com.tudou.base.vendor.entity.VendorGoods;
import com.tudou.base.vendor.mapper.VendorGoodsMapper;
import com.tudou.base.vendor.mapper.VendorMapper;
import com.tudou.base.vendor.service.IVendorGoodsService;
import com.tudou.extra.enums.ServerFlag;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import com.tudou.util.ArrayUtil;
import com.tudou.util.EntityUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("eidpVendorGoodsService")
public class VendorGoodsServiceImpl extends BaseServiceImpl<VendorGoods, String> implements IVendorGoodsService {

    @Resource
    @BaseResource
    private VendorGoodsMapper vendorGoodsMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private VendorMapper vendorMapper;

    @Override
    public void saveVendorGoods(String[] goodsIds, BigDecimal[] prices, String vendorId) throws Exception {
        String vendorName = vendorMapper.findVendorNameById(vendorId);
        if (StringUtils.isEmpty(vendorName)) {
            throw new Exception("查询不到对应的供应商！【" + vendorId + "】");
        }
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ArrayUtil.arrayToList(goodsIds));
        List<Goods> goodses = goodsMapper.selectByExample(example);
        if (goodses.size() != prices.length) {
            throw new Exception("查询到的产品数量和输入的金额数量不相等!");
        }
        Goods goods = null;
        VendorGoods vendorGoods = null;
        for (int i = 0; i < goodses.size(); i++) {
            goods = goodses.get(i);
            vendorGoods = new VendorGoods();
            vendorGoods.setGoodsId(goods.getId());
            vendorGoods.setGoodsName(goods.getGoodsName());
            vendorGoods.setGoodsTypeId(goods.getGoodsType());
            vendorGoods.setGoodsTypeName(goods.getGoodsTypeName());
            vendorGoods.setGoodsUnitId(goods.getGoodsUnit());
            vendorGoods.setGoodsUnitName(goods.getGoodsUnitName());
            vendorGoods.setGoodsBrandId(goods.getGoodsBrand());
            vendorGoods.setGoodsBrandName(goods.getGoodsBrandName());
            vendorGoods.setGoodsModel(goods.getGoodsModel());
            vendorGoods.setVendorId(vendorId);
            vendorGoods.setVendorName(vendorName);
            vendorGoods.setNormalPrice(prices[i]);
            vendorGoods.setServerFlag(ServerFlag.NORMAL.getCode());
            EntityUtil.setEntityCreateInfo(vendorGoods);
            vendorGoodsMapper.insert(vendorGoods);
        }
    }

    /**
     * 将集合转换为map
     *
     * @param list
     * @return
     */
    private Map<String, Goods> listToMap(List<Goods> list) {
        Map<String, Goods> map = new HashMap<>();
        for (Goods goods : list) {
            map.put(goods.getId(), goods);
        }
        return map;
    }

    @Override
    public DataSourceResult loadData(Class c, DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        Example example = new Example(c);
        Page<VendorGoods> page = PageHelper.startPage(dataSourceRequest.getPage(), dataSourceRequest.getPageSize());
        // 将实体的属性与数据库属性进行映射
        EntityUtil.buidSqlByRequest(dataSourceRequest, example);
        List<Example.Criteria> criteria = example.getOredCriteria();
        criteria.get(0).andCondition("vendor_id = ", request.getParameter("vendorId"));
        // 执行查询之后会自动把值设置到page中
        vendorGoodsMapper.selectByExample(example);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(page);
        dataSourceResult.setTotal(page.getTotal());
        return dataSourceResult;
    }
}