package com.xkcoding.secure.demo.controller;

import com.xkcoding.magic.core.tool.api.R;
import com.xkcoding.magic.secure.annotation.Secure;
import com.xkcoding.secure.demo.config.support.CustomSecureExpressionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试Controller,鉴权表达式，自定义，{@link CustomSecureExpressionHandler}
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/21 10:18
 */
@Secure("permitAll()")
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TestController {
    /**
     * testSecure鉴权
     */
    @Secure("testSecure(#key)")
    @GetMapping("/test")
    public R<String> test(String key) {
        return R.success("你拥有" + key + "的权限");
    }
}
