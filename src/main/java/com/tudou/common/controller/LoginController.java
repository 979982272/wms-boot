package com.tudou.common.controller;

import com.tudou.base.emp.service.IEidpEmpService;
import com.tudou.system.platform.base.controller.BaseController;
import com.tudou.system.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping
public class LoginController extends BaseController {

    @Resource
    private IEidpEmpService eidpEmpService;

    @RequestMapping(value = "valid", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel valid(@RequestParam(value = "userName", required = true) String userName,
                             @RequestParam(value = "password", required = true) String password) {
        StatusModel statusModel = null;
        try {
            eidpEmpService.valid(userName, password);
            statusModel = new StatusModel(true, "登录成功!");
        } catch (Exception e) {
            statusModel = new StatusModel(false, e.getMessage());
        }
        return statusModel;
    }

}
