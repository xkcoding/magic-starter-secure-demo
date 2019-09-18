package com.xkcoding.spelaopdemo.config.properties;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * <p>
 * 配置项
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 18:23
 */
@Data
@ConfigurationProperties(prefix = "secure")
public class SecureProperties {
    /**
     * jwt 配置类
     */
    private Jwt jwt = new Jwt();

    @Data
    public static class Jwt {
        /**
         * secret
         */
        private String secret = "secure";

        /**
         * token的有效时间，默认1周
         *
         * @see Duration
         */
        private Duration timeout = Duration.ofDays(7);

        /**
         * 加签的算法，默认sha512
         */
        private SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
    }

}
