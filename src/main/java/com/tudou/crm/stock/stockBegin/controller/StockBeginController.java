package com.tudou.crm.stock.stockBegin.controller;

import com.tudou.crm.stock.stockBegin.entity.StockBegin;
import com.tudou.crm.stock.stockBegin.service.IStockBeginService;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/crm/stock/stockBegin")
public class StockBeginController extends BaseCurdController<StockBegin> {

    @Resource
    @BaseResource
    private IStockBeginService stockBeginService;

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
     * 选择产品
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectGoods", method = RequestMethod.GET)
    public String selectGoods(Model model) {
        return this.view("selectGoods");
    }

    /**
     * 保存期初库存
     *
     * @param goodsIds
     * @param goodsPrice
     * @param stockAmounts
     * @return
     */
    @RequestMapping(value = "/saveStockBegin", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel saveStockBegin(@RequestParam(value = "goodsIds", required = true) String[] goodsIds, @RequestParam(value = "goodsPrice", required = true) BigDecimal[] goodsPrice,
                                      @RequestParam(value = "stockAmounts", required = true) BigDecimal[] stockAmounts, @RequestParam(value = "warehouseId", required = false) String warehouseId) {
        try {
            stockBeginService.saveStockBegin(goodsIds, goodsPrice, stockAmounts, warehouseId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "期初库存创建成功!");
    }


    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditStockBegin", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel auditStockBegin(@RequestParam("ids") String id) {
        try {
            stockBeginService.auditStockBegin(id.split(","));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "审核成功");
    }

}
