package com.tudou.system.platform.dataBase.controller;

import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.model.StatusModel;
import com.tudou.system.platform.dataBase.entity.DataBase;
import com.tudou.system.platform.dataBase.mapper.DataBaseMapper;
import com.tudou.system.platform.dataBase.service.IDataBaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
@Controller
@RequestMapping(value = "/system/dataBase")
public class DataBaseController {

    @Resource
    private IDataBaseService dataBaseService;

    @Resource
    private DataBaseMapper dataBaseMapper;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        return "/system/dataBase/index";
    }

    @RequestMapping(value = "loadData", method = RequestMethod.POST)
    @ResponseBody
    public DataSourceResult loadData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        List<DataBase> dataBases = dataBaseMapper.showTablesProvider(dataSourceRequest);
        dataSourceResult.setData(dataBases);
        dataSourceResult.setTotal(dataBases.size());
        return dataSourceResult;
    }

    @RequestMapping(value = "/sub", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel sub(@RequestParam("tables") String[] ids, @RequestParam("packs") String[] pack) {
        try {
            dataBaseService.createCode(ids, pack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StatusModel(true, "");
    }

}
