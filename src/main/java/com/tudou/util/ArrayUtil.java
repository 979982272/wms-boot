package com.tudou.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组工具
 *
 * @author weihua
 * @create 2017-05-08上午 1:07
 */
public final class ArrayUtil {
    /**
     * 数组转集合
     *
     * @param array
     * @return
     */
    public static List<Object> arrayToList(Object[] array) {
        List list = new ArrayList(array.length);
        for (Object o : array) {
            list.add(o);
        }
        return list;
    }
}
