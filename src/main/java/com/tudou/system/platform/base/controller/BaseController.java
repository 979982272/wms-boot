package com.tudou.system.platform.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础控制器
 *
 * @author weihua
 * @create 2017-04-23 9:38
 */
public abstract class BaseController {

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public String view(String path) {
        String currentViewPrefix = "";
        RequestMapping requestMapping = (RequestMapping) AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }
        return currentViewPrefix + "/" + path;
    }

    /**
     * 设置项目路径
     *
     * @param model
     * @param request
     */
    @ModelAttribute
    public void setModel(Model model, HttpServletRequest request) {
        model.addAttribute("ctx", request.getContextPath());
        model.addAttribute("title", "仓储管理系统");
    }


    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        return this.view("index");
    }


}
