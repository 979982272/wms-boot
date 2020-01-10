package com.tudou.common.service;


import com.tudou.base.goods.entity.Goods;

import java.util.List;
import java.util.Map;

/**
 * 通用服务接口
 *
 * @author weihua
 * @create 2017-05-15 18:51
 */
public interface ICommonService {
    /**
     * 保存时验证id是否重复
     * @param entity
     * @throws Exception
     */
    void saveValidId(Object entity) throws Exception;

    /**
     * 通过id列表查询产品并且转换为map
     * @param goods
     * @return
     * @throws Exception
     */
    Map<String,Goods> findGoodsByIdsAndToMap(List<String> goods)throws Exception;
}
