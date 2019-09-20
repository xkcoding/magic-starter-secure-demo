# annotation-demo

> 基于配置文件的权限配置

## 使用方法

```yaml
secure:
  white-list: /login
  rule-list:
    - path: /class
      expression: "anon()"
    - path: /me
      method: GET
      expression: "hasLogin()"
    - path: /spring
      expression: "@test.hasPermission(#request,#response)"
```