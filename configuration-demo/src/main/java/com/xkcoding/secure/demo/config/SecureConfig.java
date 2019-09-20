package com.xkcoding.secure.demo.config;

import com.xkcoding.magic.secure.enums.HttpMethod;
import com.xkcoding.magic.secure.support.SecureRuleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Secure 配置
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/20 14:55
 */
@Configuration
public class SecureConfig {

    @Bean
    public SecureRuleRegistry secureRuleRegistry() {
        return new SecureRuleRegistry()
                .addRule("/login", HttpMethod.GET, "anon()")
                .addRule("/me", HttpMethod.GET,"hasLogin()")
                .addRule("/**", HttpMethod.ANY, "@test.hasPermission(#request,#response)");
    }
}
