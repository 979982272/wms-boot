package com.tudou.base.menu.service.impl;

import com.tudou.base.menu.entity.EidpMenu;
import com.tudou.base.menu.entity.Items;
import com.tudou.base.menu.mapper.EidpMenuMapper;
import com.tudou.base.menu.service.IEidpMenuService;
import com.tudou.extra.system.AuthInfo;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional(rollbackFor = Exception.class)
@Service("eidpMenuService")
public class EidpMenuServiceImpl extends BaseServiceImpl<EidpMenu, String> implements IEidpMenuService {

    @Resource
    @BaseResource
    private EidpMenuMapper eidpMenuMapper;

    @Override
    public List<Items> getMenus() throws Exception {
        List<EidpMenu> eidpMenus = eidpMenuMapper.findAllMenusByLevel(1);
        return null;
    }


    @Override
    public Set<EidpMenu> getMenusByEmp(AuthInfo authInfo) throws Exception {
        // 否决该方案，需要从数据库中一次性查询出所有的菜单而不是多次去查询
        List<EidpMenu> empMenus = eidpMenuMapper.findMenuByEmpIdAndOrganizationId(authInfo.getEmpId(), authInfo.getDepartment());
        Set<EidpMenu> menus = new HashSet<>();
        for (EidpMenu menu : empMenus) {
            menus.add(menu);
            // 拿出当前的所有上级
            for (EidpMenu e = menu.getParentMenu(); e != null; e = e.getParentMenu()) {
                menus.add(e);
            }
            menus.addAll(menu.getChildMenus());
        }
        return menus;
    }


}