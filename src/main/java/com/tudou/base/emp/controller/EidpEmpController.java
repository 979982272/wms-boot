package com.tudou.base.emp.controller;

import com.tudou.base.emp.entity.EidpEmp;
import com.tudou.base.emp.service.IEidpEmpService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.StatusModel;
import com.tudou.system.platform.base.model.TreeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/base/emp")
public class EidpEmpController extends BaseCurdController<EidpEmp> {

    @Resource
    @BaseResource
    private IEidpEmpService eidpEmpService;

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultInfo", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel getDefaultInfo() {
        Map map = new HashMap();
        EidpEmp eidpEmp = new EidpEmp();
        String id = eidpEmpService.getCreateEidpEmpId();
        eidpEmp.setId(id);
        map.put("modelData", eidpEmp);
        StatusModel statusModel = new StatusModel(true, map);
        return statusModel;
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        StatusModel statusModel = null;
        try {
            EidpEmp t = eidpEmpService.selectById(id);
            Map<String, Object> map = new HashMap<>();
            map.put("modelData", t);
            statusModel = new StatusModel(true, map);
        } catch (Exception e) {
            statusModel = new StatusModel(false, e.getMessage());
        }
        return statusModel;
    }

    @RequestMapping(value = "getDropDownDataSource", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeModel> getDropDownDataSource(@RequestParam(value = "EmployeeId", required = false) String EmployeeId) {
        List<TreeModel> list = new ArrayList<>();
        TreeModel treeModel = new TreeModel();
        treeModel.setEmployeeId("123");
        treeModel.setFullNamel("456");
        treeModel.setHasEmployees(true);
        list.add(treeModel);
        return list;
    }

    /**
     * 选择负责人
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectEmp", method = RequestMethod.GET)
    public String selectEmp(Model model) {
        return this.view("selectEmp");
    }
}
