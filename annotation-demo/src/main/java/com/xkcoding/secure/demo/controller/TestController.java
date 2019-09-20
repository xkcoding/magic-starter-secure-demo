package com.xkcoding.secure.demo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import com.xkcoding.magic.core.tool.api.R;
import com.xkcoding.magic.secure.annotation.CurrentUser;
import com.xkcoding.magic.secure.annotation.Secure;
import com.xkcoding.magic.secure.constants.SecureConstants;
import com.xkcoding.magic.secure.model.SecureUser;
import com.xkcoding.magic.secure.util.JwtUtil;
import com.xkcoding.magic.secure.util.SecureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试Controller,内置语法参考 {@link com.xkcoding.magic.secure.support.SecureExpressionHandler}
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 15:30
 */
@Secure("permitAll()")
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TestController {
    private final SecureUtil secureUtil;
    private final JwtUtil jwtUtil;

    /**
     * anon() 等同于 permitAll()
     * 允许任何人访问
     */
    @Secure("anon()")
    @GetMapping("/login")
    public R<Dict> login() {
        SecureUser secureUser = new SecureUser();
        secureUser.setId(1L);
        secureUser.setUsername("xkcoding");
        secureUser.setPermissions(CollUtil.newHashSet("user::list::add", "user::list::delete"));
        String token = jwtUtil.generateToken(secureUser);

        return R.success(Dict.create().set("token", token).set("type", SecureConstants.BEARER).set("header", SecureConstants.AUTHORIZATION_HEADER).set(SecureConstants.AUTHORIZATION_HEADER, SecureConstants.BEARER + token));
    }

    /**
     * 不加注解不做权限拦截
     * 方法上找不到会去找类上的 @Secure
     */
    @GetMapping("/class")
    public String testClass() {
        return "class";
    }

    /**
     * denyAll() 拒绝所有请求
     */
    @Secure("denyAll()")
    @GetMapping("/method")
    public String method() {
        return "method";
    }

    /**
     * '@test.hasPermission(#permission)' 表示调用  Spring 容器中名为 test 的 Bean 的 hasPermission 方法
     * #permission 代表方法上的入参 permission，如何传参，参考 SpEL 语法
     * 测试：
     * 访问：http://localhost:8080/spring?permission=admin
     * 访问：http://localhost:8080/spring?permission=123
     */
    @Secure("@test.hasPermission(#permission)")
    @GetMapping("/spring")
    public String springBean(String permission) {
        return "spring bean";
    }

    /**
     * hasLogin() 表示需要登录之后才能访问
     * 支持多个表达式组合使用
     * '@CurrentUser' 可以注入当前登入用户的信息
     */
    @Secure("hasLogin() and hasPermission('user::list::*')")
    @GetMapping("/hasLogin")
    public R<SecureUser> testFun(@CurrentUser SecureUser user) {
        return R.success(user);
    }

    /**
     * hasAnyPermission('xxx1','xxx2') 表示包含 xxx1 或者 xxx2 任意权限标识均可访问
     */
    @Secure("hasAnyPermission('user::list::add','xxxxx')")
    @GetMapping("/user/add")
    public String testPermissionAdd() {
        return "has [user::list::add] permission";
    }

    /**
     * hasPermission('xxx') 表示满足 xxx 的权限标识才可以访问，xxx 支持通配符
     */
    @Secure("hasPermission('user::list::*')")
    @GetMapping("/user/select")
    public String testPermissionSelect() {
        return "has " + secureUtil.getCurrentUser().getPermissions() + " permission";
    }
}
