package com.xkcoding.secure.demo.config;

import com.xkcoding.magic.secure.support.SecureExpressionHandler;
import com.xkcoding.secure.demo.config.support.CustomSecureExpressionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 自动装配
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/21 10:18
 */
@Configuration
public class SecureConfig {
    @Bean
    public SecureExpressionHandler secureExpressionHandler(){
        return new CustomSecureExpressionHandler();
    }
}
