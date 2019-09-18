package com.xkcoding.spelaopdemo.util;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

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
}
