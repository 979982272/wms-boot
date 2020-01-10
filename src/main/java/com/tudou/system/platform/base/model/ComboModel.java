package com.tudou.system.platform.base.model;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉框数据源
 *
 * @author weihua
 * @create 2017-04-23 11:28
 */
public class ComboModel {
    private String id;
    private String text;

    public ComboModel(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public static List<ComboModel> convert(String[][] array, boolean addNull) {
        if (array != null && array.length > 0) {
            ArrayList list = new ArrayList();
            if (addNull) {
                ComboModel i = new ComboModel("", "");
                list.add(i);
            }
            for (int var5 = 0; var5 < array.length; ++var5) {
                if (StringUtils.isNotEmpty(array[var5][0])){
                    ComboModel model = new ComboModel(array[var5][0], array[var5][1]);
                    list.add(model);
                }
            }
            return list;
        } else {
            return null;
        }
    }

    public static List<ComboModel> convert(List src, boolean addNull) {
        if (src != null && !src.isEmpty()) {
            ArrayList list = new ArrayList();
            if (addNull) {
                ComboModel len = new ComboModel("", "");
                list.add(len);
            }

            int var7 = src.size();
            for (int i = 0; i < var7; ++i) {
                Object[] obj = (Object[]) ((Object[]) src.get(i));
                ComboModel model = new ComboModel(obj[0].toString(), obj[1].toString());
                list.add(model);
            }
            return list;
        } else {
            return null;
        }
    }

    public static List<ComboModel> convert(String[][] array) {
        return convert((String[][]) array, false);
    }

    public static List<ComboModel> convert(List src) {
        return convert((List) src, false);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
