package com.tudou.util;

import java.math.BigDecimal;

/**
 * 计算类
 *
 * @author weihua
 * @create 2017-05-17 17:44
 */
public final class MathUtil {
    public static BigDecimal mathRate(BigDecimal unitPrice, BigDecimal rate) {
        // 如果单价等于0则直接返回
        if (BigDecimal.ZERO.compareTo(unitPrice) == 0) {
            return unitPrice;
        }
        // 如果税率等于零则直接返回单价
        if (BigDecimal.ZERO.compareTo(rate) == 0) {
            return unitPrice;
        }
        return unitPrice.subtract(unitPrice.multiply(rate.divide(new BigDecimal("100"))));
    }
}
