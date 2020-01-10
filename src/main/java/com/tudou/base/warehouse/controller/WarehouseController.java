package com.tudou.base.warehouse.controller;

import com.tudou.base.warehouse.entity.Warehouse;
import com.tudou.base.warehouse.service.IWarehouseService;
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
@RequestMapping("/base/warehouse")
public class WarehouseController extends BaseCurdController<Warehouse> {

    @Resource
    @BaseResource
    private IWarehouseService warehouseService;

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
    @RequestMapping(value = "findWarehouseCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findWarehouseCombo() {
        List<ComboModel> list = new ArrayList<>();
        List<Warehouse> warehouses = warehouseService.selectAll();
        String[][] array = new String[warehouses.size()+1][warehouses.size()+1];
        for (int i = 0; i < warehouses.size(); i++) {
            array[i][0] = warehouses.get(i).getId();
            array[i][1] = warehouses.get(i).getWarehouseName();
        }
        return ComboModel.convert(array, true);
    }
}
