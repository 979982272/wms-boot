package com.tudou.system.development.mapper;

import com.tudou.system.development.entity.Development;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 数据列信息接口
 *
 * @author weihua
 * @create 2017-04-23 15:47
 */
public interface DevelopmentMapper {
    public List<Development> findAllColumn(String table);

    @SelectProvider(type = DevelopmentMapperDynamic.class, method = "findAllColumForProvider")
    public List<Development> findAllColumForProvider(String table);
}
