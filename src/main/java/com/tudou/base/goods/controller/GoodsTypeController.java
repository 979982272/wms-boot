package com.tudou.base.goods.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tudou.base.goods.entity.GoodsType;
import com.tudou.base.goods.mapper.GoodsTypeMapper;
import com.tudou.base.goods.service.IGoodsTypeService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.ComboModel;
import com.tudou.system.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/base/goodsType")
public class GoodsTypeController extends BaseCurdController<GoodsType> {

    @Resource
    @BaseResource
    private IGoodsTypeService baseGoodsTypeService;

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

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

    // 载入所有数据
    @RequestMapping(value = "loadGoodsTypeData", method = RequestMethod.GET)
    @ResponseBody
    public String loadGoodsTypeData(HttpServletRequest request) {
        List<GoodsType> goodsTypes = goodsTypeMapper.selectAll();
        return JSON.toJSONString(goodsTypes, SerializerFeature.WriteMapNullValue);
    }

    // 添加下一级
    @RequestMapping(value = "/addNext", method = RequestMethod.GET)
    public String addNext(Model model, @RequestParam(value = "parentId", required = false) String id) {
        model.addAttribute("parentId", id);
        return this.view("edit");
    }

    /**
     * 产品类型下拉框数据源
     *
     * @return
     */
    @RequestMapping(value = "findCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findCombo() {
        List<GoodsType> goodsTypes = goodsTypeMapper.selectAll();
        String[][] array = new String[goodsTypes.size() + 1][goodsTypes.size() + 1];
        for (int i = 0; i < goodsTypes.size(); i++) {
            array[i][0] = goodsTypes.get(i).getId();
            array[i][1] = goodsTypes.get(i).getName();
        }
        return ComboModel.convert(array, true);
    }
}
