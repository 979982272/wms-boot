package com.tudou.common.controller;

import com.tudou.common.service.ICommonService;
import com.tudou.system.platform.base.controller.BaseController;
import com.tudou.system.platform.base.model.ComboModel;
import com.tudou.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 公用控制器
 *
 * @author weihua
 * @create 2017-05-15 18:47
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {

    @Resource
    private ICommonService commonService;

    /**
     * 通过包名获取枚举的下拉框数据源
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/getComboModelDataSource")
    @ResponseBody
    public List<ComboModel> getComboModelDataSource(@RequestParam("key") String key) {
        List<ComboModel> comboModels = null;
        try {
            return CommonUtil.getComboModelByKey(key);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
