package com.xkcoding.spelaopdemo.support;

import com.xkcoding.spelaopdemo.model.User;
import com.xkcoding.spelaopdemo.util.SecureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.PatternMatchUtils;

/**
 * <p>
 * 鉴权表达式处理
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 16:38
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecureExpressionHandler {
    private final SecureUtil secureUtil;

    /**
     * 登录用户均可访问
     *
     * @return 只要登录，返回 true，反之 false
     */
    public boolean hasLogin() {
        return secureUtil.getCurrentUser() != null;
    }

    /**
     * 匿名即可访问
     *
     * @return true
     */
    public boolean anon() {
        return permitAll();
    }

    /**
     * 放行所有请求
     *
     * @return true
     */
    public boolean permitAll() {
        return true;
    }

    /**
     * 拒绝所有请求
     *
     * @return false
     */
    public boolean denyAll() {
        return false;
    }

    /**
     * 拥有指定权限才可以访问
     *
     * @param permission 权限标识
     * @return 拥有指定权限，返回 true，反之 false
     */
    public boolean hasPermission(String permission) {
        return hasAnyPermission(permission);
    }

    /**
     * 拥有任意权限均可访问
     *
     * @param permissions 权限标识列表
     * @return 满足其中一个权限，返回 true，反之 false
     */
    public boolean hasAnyPermission(String... permissions) {
        User currentUser = secureUtil.getCurrentUser();
        if (currentUser == null) {
            return false;
        }
        // 使用 PatternMatchUtils 支持 * 号
        return currentUser.getPermissions().stream().anyMatch(x -> PatternMatchUtils.simpleMatch(permissions, x));
    }
}
