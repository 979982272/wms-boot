package com.tudou.base.menu.controller;

import com.tudou.base.menu.entity.EidpMenu;
import com.tudou.base.menu.entity.Node;
import com.tudou.base.menu.mapper.EidpMenuMapper;
import com.tudou.base.menu.service.IEidpMenuService;
import com.tudou.extra.system.AuthInfo;
import com.tudou.extra.system.UserInfoUtil;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/base/menu")
public class EidpMenuController extends BaseCurdController<EidpMenu> {

    @Resource
    @BaseResource
    private IEidpMenuService eidpMenuService;

    @Resource
    private EidpMenuMapper eidpMenuMapper;

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultInfo", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel getDefaultInfo() {
        Map<String, Object> info = new HashMap<>();
        StatusModel statusModel = new StatusModel(true, info);
        return statusModel;
    }

    @RequestMapping(value = "getMenus", method = RequestMethod.POST)
    @ResponseBody
    public List<Node<Integer, String>> getMenus() throws Exception {
        List<EidpMenu> eidpMenus = eidpMenuMapper.findEidpMenu();
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        Node<Integer, String> node = new Node<Integer, String>();
        for (EidpMenu menu : eidpMenus) {
            node.put(menu.getId(), menu.getParentId(), menu.getText(), menu.getUrl(), menu.getIcon());
        }
        return node.toCollection();
    }


}
