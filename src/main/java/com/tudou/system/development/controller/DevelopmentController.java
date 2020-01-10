package com.tudou.system.development.controller;

import com.tudou.system.development.entity.Development;
import com.tudou.system.development.mapper.DevelopmentMapper;
import com.tudou.system.development.service.IDevelopmentService;
import com.tudou.system.platform.base.controller.BaseController;
import com.tudou.system.platform.base.model.ComboModel;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.model.StatusModel;
import com.tudou.system.platform.dataBase.entity.DataBase;
import com.tudou.system.platform.dataBase.mapper.DataBaseMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 开发管理
 *
 * @author weihua
 * @create 2017-04-23 10:04
 */
@Controller
@RequestMapping(value = "/system/development")
public class DevelopmentController extends BaseController {

    @Resource
    private DataBaseMapper dataBaseMapper;

    @Resource
    private DevelopmentMapper developmentMapper;

    @Resource
    private IDevelopmentService developmentService;


    /**
     * 查询所有数据库表
     *
     * @return
     */
    @RequestMapping(value = "findDataBaseCombo")
    @ResponseBody
    public List<ComboModel> findDataBaseCombo(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        List<DataBase> dataBases = dataBaseMapper.showTables();
        String[][] array = new String[dataBases.size() + 1][dataBases.size() + 1];
        for (int i = 0; i < dataBases.size(); i++) {
            array[i][0] = dataBases.get(i).getTableName();
            array[i][1] = dataBases.get(i).getTableName();
        }
        return ComboModel.convert(array, true);
    }

    /**
     * 查询表中的所有字段
     *
     * @param table
     * @return
     */
    @RequestMapping(value = "findColumnInfoByTable")
    @ResponseBody
    public DataSourceResult findColumnInfoByTable(@RequestParam(value = "table", required = true) String table) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        List<Development> developments = developmentMapper.findAllColumn(table);
        int sort = 10;
        for (Development development : developments) {
            development.setSort(sort);
            sort = sort + 10;
        }
        dataSourceResult.setData(developments);
        dataSourceResult.setTotal(developments.size());
        return dataSourceResult;
    }

    /**
     * 创建首页表格
     *
     * @param developments
     * @return
     */
    @RequestMapping(value = "/{htmlName}/createGridHtml", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel createGridHtml(@RequestBody List<Development> developments, @RequestParam(value = "src", required = true) String src, @PathVariable("htmlName") String htmlName) {
        try {
            developmentService.createGridHtml(developments, src, htmlName);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "创建成功!");
    }

    /**
     * 创建编辑页面
     *
     * @param developments
     * @param src
     * @param htmlName
     * @return
     */
    @RequestMapping(value = "/{htmlName}/createFormHtml", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel createFormHtml(@RequestBody List<Development> developments, @RequestParam(value = "src", required = true) String src, @PathVariable("htmlName") String htmlName) {
        try {
            developmentService.createFormHtml(developments, src, htmlName);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "创建成功!");
    }
}
