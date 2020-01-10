package com.tudou.system.platform.base.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tudou.system.platform.base.inject.InjectBaseDependency;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.service.IBaseService;
import com.tudou.util.EntityUtil;
import com.tudou.util.ReflectionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26 0026.
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements IBaseService<T, ID>, InitializingBean {
    public static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    private Mapper<T> mapper;

    public void setMapper(Mapper<T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        InjectBaseDependency.findInjectBaseMapperDependency(this);
        Assert.notNull(this, "Mapper没有设置:类名【" + this.getClass() + "】;继承了【IBaseService】的类必须拥有一个继承于【Mapper】的属性并且注解【@BaseResource】!");
    }

    @Override
    public T selectById(ID id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(ID id) throws Exception {
        // 判断类是否有deleteFlag表示，如果有则修改servcer_flag字段
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public T save(T t) throws Exception {
        EntityUtil.validRequired(t);
        EntityUtil.setEntityCreateInfo(t);
        saveValidId(t);
        mapper.insertSelective(t);
        return t;
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void update(T t) throws Exception {
        EntityUtil.validRequired(t);
        EntityUtil.setEntityModifyInfo(t);
        updateValidId(t);
        mapper.updateByPrimaryKey(t);
    }


    @Override
    public void deleteBatchByIds(ID[] ids) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            deleteById(ids[i]);
        }
    }

    @Override
    public DataSourceResult loadData(Class c, DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        Example example = new Example(c);
        Page<T> page = PageHelper.startPage(dataSourceRequest.getPage(), dataSourceRequest.getPageSize());
        // 将实体的属性与数据库属性进行映射
        EntityUtil.buidSqlByRequest(dataSourceRequest, example);
        // 执行查询之后会自动把值设置到page中
        mapper.selectByExample(example);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(page);
        dataSourceResult.setTotal(page.getTotal());
        return dataSourceResult;
    }


    /**
     * 验证id是否重复
     *
     * @param entity
     * @throws Exception
     */
    private void saveValidId(Object entity) throws Exception {
        String entityName = entity.getClass().getName();
        Class entityClass = Class.forName(entityName);
        Object value = ReflectionUtil.getFieldValueByClass(entity, "id");
        List list = selectT(entityClass, value);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new Exception("主键重复!【" + value + "】");
        }
    }

    /**
     * 更新的时候验证是否存在这条记录
     *
     * @param t
     * @throws Exception
     */
    public void updateValidId(Object t) throws Exception {
        String entityName = t.getClass().getName();
        Class entityClass = Class.forName(entityName);
        Object value = ReflectionUtil.getFieldValueByClass(t, "id");
        List list = selectT(entityClass, value);
        if (CollectionUtils.isEmpty(list)) {
            throw new Exception("通过【" + value + "】查询不到对应的记录！");
        }
    }

    /**
     * 通过id进行查询
     *
     * @param entityClass
     * @param value
     * @return
     * @throws Exception
     */
    private List selectT(Class entityClass, Object value) throws Exception {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("id = ", value);
        List list = mapper.selectByExample(example);
        return list;
    }

}
