package com.tudou.crm.sales.controller;

import com.tudou.crm.sales.entity.SalesOrder;
import com.tudou.crm.sales.mapper.SalesOrderMapper;
import com.tudou.crm.sales.service.ISalesOrderService;
import com.tudou.extra.enums.OrderTypeEnum;
import com.tudou.extra.enums.sales.SalesOrderStatusEnum;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.model.StatusModel;
import com.tudou.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/crm/sales/salesOrder")
public class SalesOrderController extends BaseCurdController<SalesOrder> {

    @Resource
    @BaseResource
    private ISalesOrderService salesOrderService;

    @Resource
    private SalesOrderMapper salesOrderMapper;

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultInfo", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel getDefaultInfo() {
        Map<String, Object> info = new HashMap<>();
        SalesOrder salesOrder = new SalesOrder();
        String id = CommonUtil.getIdByCode(OrderTypeEnum.SALESORDER.getCode());
        salesOrder.setOrderDate(new Date());
        salesOrder.setId(id);
        salesOrder.setStatus(SalesOrderStatusEnum.CREATE.getCode());
        info.put("modelData", salesOrder);
        StatusModel statusModel = new StatusModel(true, info);
        return statusModel;
    }

    /**
     * 创建页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String editView(Model model) {
        return this.view("create");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        StatusModel statusModel = null;
        try {
            SalesOrder t = salesOrderMapper.findSalesOrderById(id);
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
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/{id}/auditById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel auditById(@PathVariable("id") String id) {
        try {
            salesOrderService.auditById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "审核成功！");
    }
}
