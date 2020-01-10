package com.tudou.crm.storage.outStorage.controller;

import com.tudou.crm.storage.outStorage.entity.OutStorageWorkPart;
import com.tudou.crm.storage.outStorage.mapper.OutStorageWorkPartMapper;
import com.tudou.crm.storage.outStorage.service.IOutStorageWorkPartService;
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
@RequestMapping("/crm/outStorage/outStorageWorkPart")
public class OutStorageWorkPartController extends BaseCurdController<OutStorageWorkPart> {

    @Resource
    @BaseResource
    private IOutStorageWorkPartService outStorageWorkPartService;

    @Resource
    private OutStorageWorkPartMapper outStorageWorkPartMapper;

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
            List<OutStorageWorkPart> outStorageWorkParts = outStorageWorkPartMapper.findOutStorageWorkPartByOutStorageWork(id);
            dataSourceResult.setTotal(outStorageWorkParts.size());
            dataSourceResult.setData(outStorageWorkParts);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    /**
     * 明细出库
     *
     * @return
     */
    @RequestMapping(value = "outStorageByPart", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel outStorageByPart(@RequestParam(value = "id", required = true) String id,
                                        @RequestParam(value = "deliveryAmount", required = true) BigDecimal deliveryAmount) {
        try {
            outStorageWorkPartService.outStorageByPart(id,deliveryAmount);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "入库成功！");
    }
}
