package com.tudou.system.platform.init;

import com.tudou.util.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 系统初始化
 *
 * @author weihua
 * @create 2017-05-07下午 10:24
 */
public class SystemInit implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            ApplicationContext context = event.getApplicationContext();
            SpringUtil.setApplicationContext(context);
        }
    }
}
