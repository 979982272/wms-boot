package com.tudou.base.goods.service.impl;

import com.tudou.base.goods.entity.GoodsType;
import com.tudou.base.goods.mapper.GoodsTypeMapper;
import com.tudou.base.goods.service.IGoodsTypeService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("baseGoodsTypeService")
public class GoodsTypeServiceImpl extends BaseServiceImpl<GoodsType, String> implements IGoodsTypeService {

    @Resource
    @BaseResource
    private GoodsTypeMapper baseGoodsTypeMapper;

    @Override
    public List<GoodsType> loadGoodsTypeDate(String id) {
        return null;
    }

    @Override
    public DataSourceResult loadData(Class c, DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        List<GoodsType> goodsTypes = baseGoodsTypeMapper.findAllGoodsTypeByIdIsNull();
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(goodsTypes);
        dataSourceResult.setTotal(goodsTypes.size());
        return dataSourceResult;
    }
}