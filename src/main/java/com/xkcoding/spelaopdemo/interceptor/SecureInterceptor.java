package com.xkcoding.spelaopdemo.interceptor;

import com.xkcoding.spelaopdemo.enums.HttpMethod;
import com.xkcoding.spelaopdemo.exception.SecureException;
import com.xkcoding.spelaopdemo.model.Rule;
import com.xkcoding.spelaopdemo.support.SecureExpressionHandler;
import com.xkcoding.spelaopdemo.util.SecureCheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 鉴权拦截器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/19 10:56
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecureInterceptor extends HandlerInterceptorAdapter {
    private final List<Rule> ruleList;
    private final SecureExpressionHandler secureExpressionHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean checkResult = ruleList.stream().filter(rule -> filterRequest(request, rule.getPath(), rule.getMethod())).findFirst().map(rule -> {
            String expression = rule.getExpression();
            StandardEvaluationContext context = SecureCheckUtil.getEvaluationContext(secureExpressionHandler, request, response);
            return SecureCheckUtil.checkExpression(expression, context);
        }).orElse(true);
        if (!checkResult) {
            throw new SecureException("Access Denied!");
        }
        return true;
    }

    /**
     * 过滤请求路径
     *
     * @param request 请求
     * @param path    路径
     * @param method  方法
     * @return 是否匹配
     */
    private boolean filterRequest(HttpServletRequest request, String path, HttpMethod method) {
        // 比较 HttpMethod
        boolean matchMethod = matchMethod(request, method);
        boolean matchPath = matchPath(request, path);
        return matchMethod && matchPath;
    }

    /**
     * 匹配路径
     *
     * @param request 请求
     * @param path    路径
     * @return 是否匹配
     */
    private boolean matchPath(HttpServletRequest request, String path) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(path, request.getServletPath());
    }

    /**
     * 匹配方法
     *
     * @param request 请求
     * @param method  方法
     * @return 是否匹配
     */
    private boolean matchMethod(HttpServletRequest request, HttpMethod method) {
        if (method == null) {
            return false;
        } else if (method == HttpMethod.ANY) {
            return true;
        } else {
            return method == HttpMethod.valueOf(request.getMethod());
        }
    }
}
