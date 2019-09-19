package com.xkcoding.spelaopdemo.util;

import com.xkcoding.spelaopdemo.constants.SecureConstants;
import com.xkcoding.spelaopdemo.support.SecureExpressionHandler;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.MethodParameter;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <p>
 * 鉴权检查工具
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 16:26
 */
public class SecureCheckUtil {
    /**
     * 表达式处理
     */
    private static final ExpressionParser PARSER = new SpelExpressionParser();

    /**
     * 检查 SPEL 表达式
     *
     * @param spel    表达式
     * @param context 上下文
     * @return {@code true} / {@code false}
     */
    public static boolean checkExpression(String spel, StandardEvaluationContext context) {
        Expression expression = PARSER.parseExpression(spel);
        Boolean result = expression.getValue(context, Boolean.class);
        return result != null ? result : false;
    }

    /**
     * 包含方法上的参数的上下文
     *
     * @param secureExpressionHandler 上下文处理器
     * @param method                  方法
     * @param args                    参数
     * @return 上下文
     */
    public static StandardEvaluationContext getEvaluationContext(SecureExpressionHandler secureExpressionHandler, Method method, Object[] args) {
        // 初始化Sp el表达式上下文，并设置 处理函数
        StandardEvaluationContext context = new StandardEvaluationContext(secureExpressionHandler);
        // 设置表达式支持spring bean
        context.setBeanResolver(new BeanFactoryResolver(SpringUtil.getContext()));
        for (int i = 0; i < args.length; i++) {
            // 读取方法参数
            MethodParameter methodParam = ClassUtil.getMethodParameter(method, i);
            // 设置方法 参数名和值 为sp el变量
            context.setVariable(methodParam.getParameterName(), args[i]);
        }
        return context;
    }

    /**
     * 设置 request/response 参数的上下文
     *
     * @param secureExpressionHandler 上下文处理器
     * @param request                 请求
     * @param response                响应
     * @return 上下文
     */
    public static StandardEvaluationContext getEvaluationContext(SecureExpressionHandler secureExpressionHandler, HttpServletRequest request, HttpServletResponse response) {
        // 初始化Sp el表达式上下文，并设置 处理函数
        StandardEvaluationContext context = new StandardEvaluationContext(secureExpressionHandler);
        // 设置表达式支持spring bean
        context.setBeanResolver(new BeanFactoryResolver(SpringUtil.getContext()));
        context.setVariable(SecureConstants.REQUEST, request);
        context.setVariable(SecureConstants.RESPONSE, response);
        return context;
    }
}
