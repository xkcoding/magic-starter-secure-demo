package com.xkcoding.spelaopdemo.config;

import com.xkcoding.spelaopdemo.support.SecureUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <p>
 * mvc装配
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/19 10:25
 */
@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AppConfig implements WebMvcConfigurer {
    private final SecureUserArgumentResolver secureUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(secureUserArgumentResolver);
    }
}
