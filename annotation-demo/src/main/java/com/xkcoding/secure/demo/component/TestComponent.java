package com.xkcoding.secure.demo.component;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 测试鉴权类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 16:42
 */
@Slf4j
@Component("test")
public class TestComponent {
    /**
     * 自定义鉴权方法
     *
     * @param permission 权限表达式
     * @return {@code true} / {@code false}
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String permission) {
        log.info("【permission】= {}", permission);
        return StrUtil.equals(permission, "admin");
    }
}
