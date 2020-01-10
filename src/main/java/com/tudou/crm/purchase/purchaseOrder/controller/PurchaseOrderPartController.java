package com.tudou.crm.purchase.purchaseOrder.controller;

import com.tudou.crm.purchase.purchaseOrder.entity.PurchaseOrderPart;
import com.tudou.crm.purchase.purchaseOrder.mapper.PurchaseOrderPartMapper;
import com.tudou.crm.purchase.purchaseOrder.service.IPurchaseOrderPartService;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crm/purchase/purchaseOrderPart")
public class PurchaseOrderPartController extends BaseCurdController<PurchaseOrderPart> {

    @Resource
    @BaseResource
    private IPurchaseOrderPartService purchaseOrderPartService;

    @Resource
    private PurchaseOrderPartMapper purchaseOrderPartMapper;

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

    @RequestMapping(value = "loadDetailData")
    @ResponseBody
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        String id = request.getParameter("id");
        try {
            List<PurchaseOrderPart> purchaseOrderParts = purchaseOrderPartMapper.findPurchaseOrderPartByPurchaseId(id);
            dataSourceResult.setTotal(purchaseOrderParts.size());
            dataSourceResult.setData(purchaseOrderParts);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    /**
     * 编辑明细
     * @param model
     * @param id
     * @param vendorId
     * @return
     */
    @RequestMapping(value = "/{vendorId}/edit", method = RequestMethod.GET)
    public String edit(Model model, @RequestParam(value = "id", required = false) String id, @PathVariable("vendorId") String vendorId) {
        model.addAttribute("id", id);
        model.addAttribute("vendorId", vendorId);
        return this.view("edit");
    }

    /**
     * 新增明细
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/{purchaseOrderId}/{vendorId}/addPurchaseOrderPart")
    public String addPurchaseOrderPart(Model model, @RequestParam(value = "id", required = false) String id,
                                       @PathVariable("purchaseOrderId") String purchaseOrderId,
                                       @PathVariable("vendorId") String vendorId) {
        model.addAttribute("id", id);
        model.addAttribute("purchaseOrderId", purchaseOrderId);
        return this.view("edit");
    }
}
