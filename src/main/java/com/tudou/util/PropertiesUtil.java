package com.tudou.util;


import tk.mybatis.mapper.util.StringUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by li on 2015/10/15.
 */
public class PropertiesUtil {

    public Properties getProperties(String str) {
        Properties properties = new Properties();
        FileInputStream is = null;
        try {
            String fileUrl = getClass().getResource("/").getPath();
            //fileUrl = fileUrl.substring(1, fileUrl.indexOf("classes")) + "classes/";
            is = new FileInputStream(fileUrl + str);
            properties.load(is);
        } catch (IOException ioexception) {
            System.out.println("Open config file failure.");
        } catch (NullPointerException e) {
            System.out.println("is is null");
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return properties;
    }

    public static String getValue(String code, Object... objects) {
        PropertiesUtil propertiesUtils = new PropertiesUtil();
        // 模板路径
        Properties properties = propertiesUtils.getProperties("sysconfig.properties");
        String value = properties.getProperty(code);
        for (int i = 0; i < objects.length; i++) {
            if (StringUtil.isNotEmpty(value) && value.indexOf("{" + i + "}") > 0) {
                value = value.replaceAll("\\{" + i + "\\}", objects[i] != null ? objects[i].toString() : "");
            }
        }
        return value;
    }


}
