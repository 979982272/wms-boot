package com.tudou.base.emp.mapper;

import com.tudou.base.emp.entity.EidpEmp;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EidpEmpMapper extends Mapper<EidpEmp> {
    /**
     * 获取数量
     *
     * @return
     */
    Integer getEidpMaxCount();

    /**
     * 通过名称查询
     *
     * @param name
     * @return
     */
    List<EidpEmp> findEidpEmpByName(String name);

    /**
     * 通过名称查询，id不重复的
     *
     * @param name
     * @param id
     * @return
     */
    List<EidpEmp> findEidpEmpByNameAndNotId(@Param("name") String name, @Param("id") String id);

    /**
     * 验证用户名，密码
     *
     * @param username
     * @param password
     * @return
     */
    List<EidpEmp> validByUserAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 验证用户
     *
     * @param username
     * @return
     */
    int validByUser(@Param("username") String username);
}