package com.tudou.base.menu.mapper;

import com.tudou.base.menu.entity.EidpMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EidpMenuMapper extends Mapper<EidpMenu> {

    /**
     * 通过等级查询
     *
     * @param level
     * @return
     */
    List<EidpMenu> findAllMenusByLevel(Integer level);

    /**
     * 通过父级id查询列表
     *
     * @param id
     * @return
     */
    List<EidpMenu> findMenusByParentId(Integer id);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    EidpMenu findMenuById(Integer id);

    /**
     * 通过用户编码和组织机构编码查询
     * @param empId
     * @param organizationId
     * @return
     */
    List<EidpMenu> findMenuByEmpIdAndOrganizationId(@Param("empId") String empId, @Param("organizationId") String organizationId);

    List<EidpMenu> findEidpMenu();
}