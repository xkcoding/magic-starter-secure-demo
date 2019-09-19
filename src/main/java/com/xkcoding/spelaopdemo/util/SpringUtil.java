package com.xkcoding.spelaopdemo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Spring 工具类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/19 14:40
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 获取当前上下文
     */
    public static ApplicationContext getContext() {
        if (applicationContext == null) {
            throw new RuntimeException("当前Spring无上下文环境");
        }
        return applicationContext;
    }
}
