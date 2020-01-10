package com.tudou.base.vendor.controller;

import com.tudou.base.vendor.entity.Vendor;
import com.tudou.base.vendor.service.IVendorService;
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
@RequestMapping("/base/vendor")
public class VendorController extends BaseCurdController<Vendor> {

    @Resource
    @BaseResource
    private IVendorService vendorService;

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
    @RequestMapping(value = "findVendorCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findVendorCombo() {
        List<ComboModel> list = new ArrayList<>();
        List<Vendor> vendors = vendorService.selectAll();
        String[][] array = new String[vendors.size()][vendors.size()];
        for (int i = 0; i < vendors.size(); i++) {
            array[i][0] = vendors.get(i).getId();
            array[i][1] = vendors.get(i).getVendorName();
        }
        return ComboModel.convert(array, true);
    }
}
