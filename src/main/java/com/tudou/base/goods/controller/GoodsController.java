package com.tudou.base.goods.controller;

import com.tudou.base.goods.entity.Goods;
import com.tudou.base.goods.service.IGoodsService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/base/goods")
public class GoodsController extends BaseCurdController<Goods> {

    @Resource
    @BaseResource
    private IGoodsService goodsArchiveService;

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
     * 通过供应商编码选择产品 多选
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/{vendorId}/multipleSelectGoods", method = RequestMethod.GET)
    public String multipleSelectGoods(Model model, @PathVariable("vendorId") String vendorId) {
        model.addAttribute("vendorId", vendorId);
        return this.view("multipleSelectGoods");
    }

    /**
     * 通过供应商编码选择产品 单选
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/{vendorId}/selectGoods", method = RequestMethod.GET)
    public String selectGoods(Model model, @PathVariable("vendorId") String vendorId) {
        model.addAttribute("vendorId", vendorId);
        return this.view("selectGoods");
    }

    /**
     * 查询产品信息，附带库存 多选
     *
     * @param model
     * @param warehouseId
     * @return
     */
    @RequestMapping(value = "/{warehouseId}/multipleSelectGoodsByStock", method = RequestMethod.GET)
    public String multipleSelectGoodsByStock(Model model, @PathVariable("warehouseId") String warehouseId) {
        model.addAttribute("warehouseId", warehouseId);
        return this.view("multipleSelectGoodsByStock");
    }

    /**
     * 查询产品信息，附带库存 单选
     *
     * @param model
     * @param warehouseId
     * @return
     */
    @RequestMapping(value = "/{warehouseId}/selectGoodsByStock", method = RequestMethod.GET)
    public String selectGoodsByStock(Model model, @PathVariable("warehouseId") String warehouseId) {
        model.addAttribute("warehouseId", warehouseId);
        return this.view("selectGoodsByStock");
    }

    /**
     * 获取供应商可以选择的产品数据,给供应商分配商品的时候使用
     *
     * @param dataSourceRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "selectVendorGoods")
    @ResponseBody
    public DataSourceResult selectVendorGoods(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request, @RequestParam(value = "vendorId", required = true) String vendorId) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        try {
            dataSourceResult = goodsArchiveService.selectVendorGoods(dataSourceRequest, request, vendorId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }
}
