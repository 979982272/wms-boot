package com.tudou.system.development.service.impl;

import com.tudou.properties.FtlProperties;
import com.tudou.properties.PathProperties;
import com.tudou.system.development.entity.Development;
import com.tudou.system.development.service.IDevelopmentService;
import com.tudou.util.CommonUtil;
import com.tudou.util.FreemakerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发管理服务实现类
 *
 * @author weihua
 * @create 2017-05-02 22:26
 */
@Transactional
@Service("DevelopmentService")
public class DevelopmentServiceImpl implements IDevelopmentService {

    @Autowired
    private FtlProperties ftlProperties;

    @Autowired
    private PathProperties pathProperties;

    @Override
    public void createGridHtml(List<Development> developments, String src, String htmlName) throws Exception {
        createHtml(developments, src, htmlName, ftlProperties.getGridHtml());
    }

    @Override
    public void createFormHtml(List<Development> developments, String src, String htmlName) throws Exception {
        createHtml(developments, src, htmlName, ftlProperties.getEditHtml());
    }

    /**
     * 创建html页面
     *
     * @param developments 数据
     * @param src          路径
     * @param htmlName     页面名称
     * @param ftl          模板位置
     * @throws Exception
     */
    private void createHtml(List<Development> developments, String src, String htmlName, String ftl) throws Exception {
        String folderPath = pathProperties.getHtml() + CommonUtil.transSrc(src);
        String filePath = folderPath + CommonUtil.transHtmlName(htmlName);
        developments = sortDevelopments(developments);
        Map map = new HashMap();
        map.put("developments", developments);
        map.put("htmlName", htmlName);
        map.put("controllerSrc", CommonUtil.transSrc(src));
        FreemakerUtil.createFileByFreemaker(map, ftl, filePath, folderPath);
    }

    /**
     * 给数据排序与转换列名
     *
     * @param list
     * @return
     */
    private List<Development> sortDevelopments(List<Development> list) {
        Development[] developments = new Development[list.size()];
        Development development = null;
        List<Development> data = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            developments[i] = list.get(i);
        }
        for (int i = 0; i < developments.length - 1; i++) {
            for (int j = 0; j < developments.length - i - 1; j++) {
                if (developments[j].getSort() > developments[j + 1].getSort()) {
                    development = developments[j];
                    developments[j] = developments[j + 1];
                    developments[j + 1] = development;
                }
            }
        }
        // 将列名字段进行转换
        for (int i = 0; i < developments.length; i++) {
            development = developments[i];
            development.setColumnName(CommonUtil.transSqlFiledToEntityFiled(development.getColumnName()));
            data.add(development);
        }
        return data;
    }

}
