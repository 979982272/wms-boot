package com.tudou.common.service.impl;

import com.tudou.base.goods.entity.Goods;
import com.tudou.base.goods.mapper.GoodsMapper;
import com.tudou.common.service.ICommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用服务实现
 *
 * @author weihua
 * @create 2017-05-15 18:51
 */
@Transactional
@Service("commonService")
public class CommonServiceImpl implements ICommonService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public void saveValidId(Object entity) throws Exception {
        String entityName = entity.getClass().getName();
        Class entityClass = Class.forName(entityName);
        Field idField = entityClass.getDeclaredField("id");
        idField.setAccessible(true);
        Object value = idField.get(entity);
    }

    @Override
    public Map<String, Goods> findGoodsByIdsAndToMap(List<String> goods) throws Exception {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", goods);
        List<Goods> goodses = goodsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(goodses) || goodses.size() != goods.size()) {
            throw new Exception("查询不到对应的产品，或查询出来的产品数量与传递的产品参数不等!");
        }
        Map<String, Goods> goodsMap = listToMap(goodses);
        return goodsMap;
    }

    /**
     * 将集合转换为map
     *
     * @param goodses
     * @return
     */
    private Map<String, Goods> listToMap(List<Goods> goodses) {
        Map<String, Goods> map = new HashMap<>();
        for (Goods goods : goodses) {
            map.put(goods.getId(), goods);
        }
        return map;
    }
}
