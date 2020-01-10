package com.tudou.crm.purchase.purchaseApply.controller;

import com.tudou.crm.purchase.purchaseApply.entity.PurchaseApply;
import com.tudou.crm.purchase.purchaseApply.mapper.PurchaseApplyMapper;
import com.tudou.crm.purchase.purchaseApply.service.IPurchaseApplyService;
import com.tudou.extra.enums.OrderTypeEnum;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.StatusModel;
import com.tudou.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/crm/purchase/purchaseApply")
public class PurchaseApplyController extends BaseCurdController<PurchaseApply> {

    @Resource
    @BaseResource
    private IPurchaseApplyService purchaseApplyService;

    @Resource
    private PurchaseApplyMapper purchaseApplyMapper;

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultInfo", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel getDefaultInfo() {
        Map<String, Object> info = new HashMap<>();
        PurchaseApply purchaseApply = new PurchaseApply();
        String id = CommonUtil.getIdByCode(OrderTypeEnum.PURCHASEAPPLY.getCode());
        purchaseApply.setId(id);
        info.put("modelData", purchaseApply);
        StatusModel statusModel = new StatusModel(true, info);
        return statusModel;
    }

    /**
     * 保存
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel create(@RequestBody PurchaseApply t) {
        Map<String, Object> map = new HashMap<>();
        try {
            PurchaseApply purchaseApply = purchaseApplyService.savePurchaseApply(t);
            map.put("purchaseApply", purchaseApply);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "保存成功!", map);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        StatusModel statusModel = null;
        try {
            PurchaseApply t = purchaseApplyMapper.findPurchaseApplyById(id);
            Map<String, Object> map = new HashMap<>();
            map.put("modelData", t);
            statusModel = new StatusModel(true, map);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            statusModel = new StatusModel(false, e.getMessage());
        }
        return statusModel;
    }

    /**
     * 跳转创建页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String edit(Model model) {
        return this.view("create");
    }

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel auditById(@RequestParam("ids") String id) {
        try {
            purchaseApplyService.auditById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "审核成功!");
    }

    /**
     * 取消审核
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cancelAuditById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel cancelAuditById(@RequestParam("ids") String id) {
        try {
            purchaseApplyService.cancelAuditById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "取消审核成功!");
    }

    /**
     * 下推采购订单
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/{purchaseApplyId}/pushPurchaseOrder", method = RequestMethod.GET)
    public String pushPurchaseOrder(Model model, @PathVariable("purchaseApplyId") String purchaseApplyId) {
        model.addAttribute("purchaseApplyId", purchaseApplyId);
        return this.view("pushPurchaseOrder");
    }
}
