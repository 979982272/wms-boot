package com.tudou.base.goods.service.impl;

import com.tudou.base.goods.entity.GoodsBrand;
import com.tudou.base.goods.mapper.GoodsBrandMapper;
import com.tudou.base.goods.service.IGoodsBrandService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class)
@Service("goodsBrandService")
public class GoodsBrandServiceImpl extends BaseServiceImpl<GoodsBrand,String> implements IGoodsBrandService{

    @Resource
    @BaseResource
    private GoodsBrandMapper goodsBrandMapper;

}