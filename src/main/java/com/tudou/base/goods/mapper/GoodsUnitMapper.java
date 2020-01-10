package com.tudou.base.goods.mapper;

import com.tudou.base.goods.entity.GoodsUnit;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsUnitMapper extends Mapper<GoodsUnit> {
    /**
     * 通过单位名称查询
     *
     * @param unitName
     * @return
     */
    List<GoodsUnit> findGoodsUnitByUnitName(@Param("unitName") String unitName);

    /**
     * 通过单位名称查询，排除单位
     *
     * @param unitName
     * @param id
     * @return
     */
    List<GoodsUnit> findGoodsUnitByUnitNameAndNotId(@Param("unitName") String unitName, @Param("id") String id);
}