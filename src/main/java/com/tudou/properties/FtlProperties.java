package com.tudou.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 模板路径配置文件实体
 */
@Component
@ConfigurationProperties(prefix = "ftl")
public class FtlProperties {
    // ##mybatis逆向工程模板文件名称
    private String generatorConfig;

    // ##控制器生成模板
    private String controller;

    // ##服务接口生成模板
    private String service;

    // ##服务接口实现生成模板
    private String serviceImpl;

    // ##表格首页生成模板
    private String gridHtml;

    // ##表格编辑页面生成模板
    private String editHtml;

    public String getGeneratorConfig() {
        return generatorConfig;
    }

    public void setGeneratorConfig(String generatorConfig) {
        this.generatorConfig = generatorConfig;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public String getGridHtml() {
        return gridHtml;
    }

    public void setGridHtml(String gridHtml) {
        this.gridHtml = gridHtml;
    }

    public String getEditHtml() {
        return editHtml;
    }

    public void setEditHtml(String editHtml) {
        this.editHtml = editHtml;
    }
}
