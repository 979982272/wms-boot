package com.tudou.base.goods.mapper;

import com.tudou.base.goods.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface GoodsMapper extends Mapper<Goods> {
    /**
     * 通过产品名称查询
     *
     * @param goodsName
     * @return
     */
    List<Goods> findGoodsByGoodsName(@Param("goodsName") String goodsName);

    /**
     * 通过产品名称查询，排除id
     *
     * @param goodsName
     * @param id
     * @return
     */
    List<Goods> findGoodsByGoodsNameAndNotId(@Param("goodsName") String goodsName, @Param("id") String id);

    /**
     * 设置为逻辑删除
     *
     * @param id
     */
    void updateGoodsByServerFlag(@Param("id") String id);

    /**
     * 通过供应商id选择供应商可以操作的产品
     *
     * @param map
     * @return
     */
    @SelectProvider(type = GoodsDynaSqlProvider.class, method = "findVendorGoodsByVendorId")
    @ResultMap(value = "BaseResultMap")
    List<Goods> findVendorGoodsByVendorId(Map<String, Object> map);
}