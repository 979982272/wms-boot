package com.tudou.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 路径配置文件实体
 */
@Component
@ConfigurationProperties(prefix = "path")
public class PathProperties {
    //##mybatis逆向工程配置文件路径
    private String generatorConfig;

    //##java生成路径
    private String java;

    //##html生成路径
    private String html;

    //##项目web-info路径
    private String webInfo;

    public String getGeneratorConfig() {
        return generatorConfig;
    }

    public void setGeneratorConfig(String generatorConfig) {
        this.generatorConfig = generatorConfig;
    }

    public String getJava() {
        return java;
    }

    public void setJava(String java) {
        this.java = java;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getWebInfo() {
        return webInfo;
    }

    public void setWebInfo(String webInfo) {
        this.webInfo = webInfo;
    }
}
