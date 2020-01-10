package com.tudou.util;

import org.springframework.context.ApplicationContext;

/**
 * Spring工具
 *
 * @author weihua
 * @create 2017-05-07下午 9:51
 */
public final class SpringUtil {
    private static ApplicationContext applicationContext;

    private SpringUtil() {
    }

    // 在系统初始化的时候讲applicationContext设置进来，SystemInit
    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> t) {
        return applicationContext.getBean(t);
    }
}
