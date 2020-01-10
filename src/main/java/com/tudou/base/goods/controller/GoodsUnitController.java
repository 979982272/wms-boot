package com.tudou.base.goods.controller;

import com.tudou.base.goods.entity.GoodsUnit;
import com.tudou.base.goods.service.IGoodsUnitService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.ComboModel;
import com.tudou.system.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/base/goodsUnit")
public class GoodsUnitController extends BaseCurdController<GoodsUnit> {

    @Resource
    @BaseResource
    private IGoodsUnitService goodsUnitService;

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
     * 产品单位下拉框数据源
     *
     * @return
     */
    @RequestMapping(value = "findCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findCombo() {
        List<GoodsUnit> goodsTypes = goodsUnitService.selectAll();
        String[][] array = new String[goodsTypes.size() + 1][goodsTypes.size() + 1];
        for (int i = 0; i < goodsTypes.size(); i++) {
            array[i][0] = goodsTypes.get(i).getId();
            array[i][1] = goodsTypes.get(i).getUnitName();
        }
        return ComboModel.convert(array, true);
    }
}
