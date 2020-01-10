package com.tudou.system.development.service;

import com.tudou.system.development.entity.Development;

import java.util.List;

/**
 * 开发管理服务
 *
 * @author weihua
 * @create 2017-05-02 22:25
 */
public interface IDevelopmentService {
    /**
     * 创建表格页面
     *
     * @param developments
     * @param src
     * @param htmlName
     * @throws Exception
     */
    void createGridHtml(List<Development> developments, String src, String htmlName) throws Exception;

    /**
     * 创建编辑页面
     *
     * @param developments
     * @param src
     * @param htmlName
     * @throws Exception
     */
    void createFormHtml(List<Development> developments, String src, String htmlName) throws Exception;
}
