package com.tudou.base.vendor.controller;

import com.tudou.base.vendor.entity.VendorGoods;
import com.tudou.base.vendor.service.IVendorGoodsService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/base/vendorGoods")
public class VendorGoodsController extends BaseCurdController<VendorGoods> {

    @Resource
    @BaseResource
    private IVendorGoodsService vendorGoodsService;

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

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("vendorId", request.getParameter("vendorId"));
        return this.view("index");
    }

    /**
     * 保存供应商的产品
     *
     * @param goodsIds
     * @param prices
     * @return
     */
    @RequestMapping(value = "/saveVendorGoods", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel saveVendorGoods(@RequestParam(value = "goodsIds", required = true) String[] goodsIds, @RequestParam(value = "prices", required = true) BigDecimal[] prices,
                                       @RequestParam(value = "vendorId", required = true) String vendorId) {
        try {
            vendorGoodsService.saveVendorGoods(goodsIds, prices, vendorId);
            return new StatusModel(true, "保存成功!");
        } catch (Exception e) {
            return new StatusModel(false, e.getMessage());
        }
    }
}
