package com.xkcoding.spelaopdemo.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 当前用户
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/19 10:06
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CurrentUser {
}
