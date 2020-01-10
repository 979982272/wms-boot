package com.tudou.crm.storage.outStorage.controller;

import com.tudou.crm.storage.outStorage.entity.OutStorageWork;
import com.tudou.crm.storage.outStorage.service.IOutStorageWorkService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/crm/outStorage/outStorageWork")
public class OutStorageWorkController extends BaseCurdController<OutStorageWork> {

    @Resource
    @BaseResource
    private IOutStorageWorkService outStorageWorkService;

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
     * 通过出库单整单出库
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/outStorageByStorageId", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel outStorageByStorageId(@RequestParam("ids") String id) {
        try {
            outStorageWorkService.outStorageByStorageId(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            e.printStackTrace();
            return new StatusModel(false,e.getMessage());
        }
        return new StatusModel(true, "出库成功!");
    }
}
