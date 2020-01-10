package com.tudou.system.platform.base.service;

import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26 0026.
 */
@Service
public interface IBaseService<T, ID extends Serializable> {

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    T selectById(ID id);

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    void deleteById(ID id) throws Exception;

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    T save(T entity) throws Exception;

    /**
     * 查找所有
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据主键更新
     *
     * @param t
     * @return
     */
    void update(T t) throws Exception;

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatchByIds(ID[] ids) throws Exception;

    /**
     * 获取列表的数据
     *
     * @param c
     * @param dataSourceRequest
     * @param request
     * @return
     */
    DataSourceResult loadData(Class c, DataSourceRequest dataSourceRequest, HttpServletRequest request);
}
