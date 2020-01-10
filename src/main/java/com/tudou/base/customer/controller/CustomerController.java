package com.tudou.base.customer.controller;

import com.tudou.base.customer.entity.Customer;
import com.tudou.base.customer.service.ICustomerService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.ComboModel;
import com.tudou.system.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/base/customer")
public class CustomerController extends BaseCurdController<Customer> {

    @Resource
    @BaseResource
    private ICustomerService customerService;

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

    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @RequestMapping(value = "findCustomerCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findWarehouseCombo() {
        List<ComboModel> list = new ArrayList<>();
        List<Customer> customers = customerService.selectAll();
        String[][] array = new String[customers.size()+1][customers.size()+1];
        for (int i = 0; i < customers.size(); i++) {
            array[i][0] = customers.get(i).getId();
            array[i][1] = customers.get(i).getCustomerName();
        }
        return ComboModel.convert(array, true);
    }
}
