package com.xkcoding.spelaopdemo.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 自定义方法、类上鉴权注解
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 15:20
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuthorize {
    /**
     * 权限表达式
     *
     * @return 权限表达式
     */
    String value();
}
