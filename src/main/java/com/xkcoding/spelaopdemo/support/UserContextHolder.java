package com.xkcoding.spelaopdemo.support;

import com.xkcoding.spelaopdemo.model.SecureUser;

/**
 * <p>
 * 缓存用户信息
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 18:06
 */
public class UserContextHolder {
    private static final ThreadLocal<SecureUser> HOLDER = new ThreadLocal<>();

    public static SecureUser get() {
        return HOLDER.get();
    }

    public static void set(SecureUser secureUser) {
        HOLDER.set(secureUser);
    }

    public static void remove() {
        HOLDER.remove();
    }
}
