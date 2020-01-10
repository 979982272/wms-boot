package com.tudou.base.goods.mapper;

import com.tudou.base.goods.entity.GoodsType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsTypeMapper extends Mapper<GoodsType> {
    List<GoodsType> findAllGoodsTypeByIdIsNull();
}