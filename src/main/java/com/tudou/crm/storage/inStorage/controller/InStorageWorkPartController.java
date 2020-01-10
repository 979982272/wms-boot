package com.tudou.crm.storage.inStorage.controller;

import com.tudou.crm.storage.inStorage.entity.InStorageWorkPart;
import com.tudou.crm.storage.inStorage.mapper.InStorageWorkPartMapper;
import com.tudou.crm.storage.inStorage.service.IInStorageWorkPartService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crm/inStorage/inStorageWorkPart")
public class InStorageWorkPartController extends BaseCurdController<InStorageWorkPart> {

    @Resource
    @BaseResource
    private IInStorageWorkPartService inStorageWorkPartService;

    @Resource
    private InStorageWorkPartMapper inStorageWorkPartMapper;

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
     * 载入明细信息
     *
     * @param dataSourceRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "loadDetailData")
    @ResponseBody
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        String id = request.getParameter("id");
        try {
            List<InStorageWorkPart> inStorageWorkParts = inStorageWorkPartMapper.findInStorageWorkPartByStorageId(id);
            dataSourceResult.setTotal(inStorageWorkParts.size());
            dataSourceResult.setData(inStorageWorkParts);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    /**
     * 明细入库
     *
     * @return
     */
    @RequestMapping(value = "inStorageByPart", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel inStorageByPart(@RequestParam(value = "id", required = true) String id,
                                       @RequestParam(value = "receivingAmount", required = true) BigDecimal receivingAmount) {
        try {
            inStorageWorkPartService.inStorageByPart(id,receivingAmount);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "入库成功！");
    }
}
