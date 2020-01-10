package com.tudou.util;

import com.tudou.system.platform.base.model.ComboModel;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 通用工具
 *
 * @author weihua
 * @create 2017-05-09下午 5:53
 */
public final class CommonUtil {

    // 自动补充文件名称结尾
    public static String transHtmlName(String htmlName) throws Exception {
        if (StringUtils.isEmpty(htmlName)) {
            throw new Exception("页面名称不能为空!");
        }
        if (!"/".equals(htmlName.substring(0, 1)) && !"\\".equals(htmlName.substring(0, 1))) {
            htmlName = "/" + htmlName;
        }
        // 如果不是以html后缀结尾的自动补齐
        if (!htmlName.endsWith(".html")) {
            htmlName = htmlName + ".html";
        }
        return htmlName;
    }


    // 修改控制器的路径
    public static String transSrc(String src) {
        // 如果没有输入访问路径 则默认/
        if (StringUtils.isEmpty(src)) {
            src = "/";
        }
        // 如果是不是/ 或者 \ 开头的则补充上去
        if (!"/".equals(src.substring(0, 1)) && !"\\".equals(src.substring(0, 1))) {
            src = "/" + src;
        }
        return src;
    }

    /**
     * 将数据库字段转换成实体类字段 ip_data->ipData
     *
     * @param sqlFiles
     * @return
     */
    public static String transSqlFiledToEntityFiled(String sqlFiles) {
        if (StringUtils.isNotEmpty(sqlFiles)) {
            while (sqlFiles.indexOf("_") > 0) {
                if (sqlFiles.indexOf("_") + 2 == sqlFiles.length()) {
                    sqlFiles = sqlFiles.substring(0, sqlFiles.indexOf("_")) + sqlFiles.substring(sqlFiles.indexOf("_") + 1, sqlFiles.length()).toUpperCase();
                } else if (sqlFiles.indexOf("_") + 2 <= sqlFiles.length()) {
                    sqlFiles = sqlFiles.substring(0, sqlFiles.indexOf("_")) + sqlFiles.substring(sqlFiles.indexOf("_") + 1, sqlFiles.indexOf("_") + 2).toUpperCase() + sqlFiles.substring(sqlFiles.indexOf("_") + 2, sqlFiles.length());
                } else {
                    sqlFiles = sqlFiles.replaceAll("_", "");
                }
            }
        }
        return sqlFiles;
    }

    /**
     * 通过数量组合成id
     *
     * @param count
     * @return
     */
    public static String transIdByCount(int count) {
        count = count + 1;
        String id = null;
        if (count < 10) {
            id = "000" + count;
        } else if (count >= 10 && count < 100) {
            id = "00" + count;
        } else if (count >= 100 && count < 1000) {
            id = "0" + count;
        } else {
            id = count + "";
        }
        return id;
    }

    /**
     * 通过编码组合主键
     *
     * @param code
     * @return
     */
    public static String getIdByCode(String code) {
        String id = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String date = simpleDateFormat.format(new Date());
        return code + date + getFourRandom();
    }

    /**
     * 产生4位随机数(0000-9999)
     *
     * @return 4位随机数
     */
    public static String getFourRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                fourRandom = "0" + fourRandom;
        }
        return fourRandom;
    }

    /**
     * 通过key获取下拉框数据源
     *
     * @param key
     * @return
     */
    public static List<ComboModel> getComboModelByKey(String key) throws Exception {
        List<ComboModel> comboModels = new ArrayList<>();
        comboModels.add(new ComboModel("", ""));
        if (key.toUpperCase().startsWith("ENUM_")) {
            key = key.substring(5);
            Class c = Class.forName("com.tudou." + key);
            String code = "code";
            String name = "name";
            if (c.isEnum()) {
                Object[] objs = c.getEnumConstants();
                Field codeField = null;
                Field nameField = null;
                String codeValue = null;
                String nameValue = null;
                ComboModel comboModel = null;
                for (Object obj : objs) {
                    codeField = obj.getClass().getDeclaredField(code);
                    nameField = obj.getClass().getDeclaredField(name);
                    codeField.setAccessible(true);
                    nameField.setAccessible(true);
                    codeValue = codeField.get(obj).toString();
                    nameValue = nameField.get(obj).toString();
                    comboModel = new ComboModel(codeValue, nameValue);
                    comboModels.add(comboModel);
                }
            }
        }
        return comboModels;
    }
}
