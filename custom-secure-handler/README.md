# custom-secure-handler

> 自定义权限控制表达式处理

## 使用方法

```java
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

@Configuration
public class SecureConfig {
    @Bean
    public SecureExpressionHandler secureExpressionHandler(){
        return new CustomSecureExpressionHandler();
    }
}
```