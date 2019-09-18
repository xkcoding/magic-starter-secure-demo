package com.xkcoding.spelaopdemo.support;

import com.xkcoding.spelaopdemo.model.User;

/**
 * <p>
 * 缓存用户信息
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 18:06
 */
public class UserContextHolder {
    private static final ThreadLocal<User> HOLDER = new ThreadLocal<>();

    public static User get() {
        return HOLDER.get();
    }

    public static void set(User user) {
        HOLDER.set(user);
    }

    public static void remove() {
        HOLDER.remove();
    }
}
