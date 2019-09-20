# annotation-demo

> 基于代码的权限配置

## 使用方法

```java
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
```