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
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TestController {
    private final SecureUtil secureUtil;
    private final JwtUtil jwtUtil;

    @GetMapping("/login")
    public R<Dict> login() {
        SecureUser secureUser = new SecureUser();
        secureUser.setId(1L);
        secureUser.setUsername("xkcoding");
        secureUser.setPermissions(CollUtil.newHashSet("user::list::add", "user::list::delete"));
        String token = jwtUtil.generateToken(secureUser);

        return R.success(Dict.create().set("token", token).set("type", SecureConstants.BEARER).set("header", SecureConstants.AUTHORIZATION_HEADER).set(SecureConstants.AUTHORIZATION_HEADER, SecureConstants.BEARER + token));
    }

    @GetMapping("/class")
    public String testClass() {
        return "class";
    }

    /**
     * denyAll() 拒绝所有请求
     * 先走拦截器，拦截器通过之后，再根据注解权限拦截
     */
    @Secure("denyAll()")
    @GetMapping("/method")
    public String method() {
        return "method";
    }

    @GetMapping("/spring")
    public String springBean(String permission) {
        return "spring bean";
    }

    @GetMapping("/me")
    public R<SecureUser> me(@CurrentUser SecureUser user) {
        return R.success(user);
    }
}
