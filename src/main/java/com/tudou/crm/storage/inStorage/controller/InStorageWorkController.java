package com.tudou.crm.storage.inStorage.controller;

import com.tudou.crm.storage.inStorage.entity.InStorageWork;
import com.tudou.crm.storage.inStorage.service.IInStorageWorkService;
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
@RequestMapping("/crm/inStorage/inStorageWork")
public class InStorageWorkController extends BaseCurdController<InStorageWork> {

    @Resource
    @BaseResource
    private IInStorageWorkService inStorageWorkService;

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
     * 通过入口单号入库
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/inStorageByStorageId", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel inStorageByStorageId(@RequestParam("ids") String id) {
        try {
            inStorageWorkService.inStorageByStorageId(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            e.printStackTrace();
            return new StatusModel(false,e.getMessage());
        }
        return new StatusModel(true, "入库成功!");
    }

}
