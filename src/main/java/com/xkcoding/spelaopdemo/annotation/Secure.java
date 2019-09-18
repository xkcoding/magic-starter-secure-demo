package com.xkcoding.spelaopdemo.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 自定义方法、类上鉴权注解，内置的权限表达式 {@link com.xkcoding.spelaopdemo.support.SecureExpressionHandler} 也可以自定义 Spring Bean 使用 {@code @} 引用
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 15:20
 * @see com.xkcoding.spelaopdemo.support.SecureExpressionHandler
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Secure {
    /**
     * 权限表达式，内置的权限表达式 {@link com.xkcoding.spelaopdemo.support.SecureExpressionHandler} 也可以自定义 Spring Bean 使用 {@code @} 引用
     *
     * @return 权限表达式
     * @see com.xkcoding.spelaopdemo.support.SecureExpressionHandler
     */
    String value();
}
