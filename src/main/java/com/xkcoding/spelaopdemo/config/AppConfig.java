package com.xkcoding.spelaopdemo.config;

import com.xkcoding.spelaopdemo.config.properties.SecureProperties;
import com.xkcoding.spelaopdemo.support.SecureExpressionHandler;
import com.xkcoding.spelaopdemo.util.JwtUtil;
import com.xkcoding.spelaopdemo.util.SecureUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 项目配置
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 18:15
 */
@Configuration
@EnableConfigurationProperties({SecureProperties.class})
public class AppConfig {
    @Bean
    public JwtUtil jwtUtil(SecureProperties properties) {
        return new JwtUtil(properties);
    }

    @Bean
    public SecureUtil secureUtil(JwtUtil jwtUtil) {
        return new SecureUtil(jwtUtil);
    }

    @Bean
    public SecureExpressionHandler secureExpressionHandler(SecureUtil secureUtil) {
        return new SecureExpressionHandler(secureUtil);
    }
}
