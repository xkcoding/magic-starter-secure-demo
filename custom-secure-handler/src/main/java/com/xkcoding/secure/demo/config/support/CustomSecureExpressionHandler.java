package com.xkcoding.secure.demo.config.support;

import cn.hutool.core.util.RandomUtil;
import com.xkcoding.magic.secure.support.SecureExpressionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 自定义权限控制表达式处理
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/21 10:20
 */
@Slf4j
public class CustomSecureExpressionHandler implements SecureExpressionHandler {
    public boolean testSecure(String key) {
        int randomInt = RandomUtil.randomInt(1, 10);
        log.info("【randomInt】= {}", randomInt);

        boolean ret = randomInt > 5;
        log.info("【key】= {}，{} 权限", key, ret ? "有" : "无");
        return ret;
    }
}
