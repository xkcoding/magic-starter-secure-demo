package com.xkcoding.spelaopdemo.aspect;

import cn.hutool.core.util.StrUtil;
import com.xkcoding.spelaopdemo.annotation.Secure;
import com.xkcoding.spelaopdemo.exception.SecureException;
import com.xkcoding.spelaopdemo.support.SecureExpressionHandler;
import com.xkcoding.spelaopdemo.util.ClassUtil;
import com.xkcoding.spelaopdemo.util.SecureCheckUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.MethodParameter;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 * 鉴权注解切面
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 15:33
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecureAspect implements ApplicationContextAware {
    private final SecureExpressionHandler secureExpressionHandler;

    @Around("@annotation(com.xkcoding.spelaopdemo.annotation.Secure) || @within(com.xkcoding.spelaopdemo.annotation.Secure)")
    public Object preAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        if (validateSecure(joinPoint)) {
            return joinPoint.proceed();
        }
        throw new SecureException("Access Denied!");
    }

    /**
     * 校验权限
     *
     * @param joinPoint 切入点
     * @return 是否有访问权限
     */
    private boolean validateSecure(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 读取权限注解，优先方法上，没有则读取类
        Secure secure = ClassUtil.getAnnotation(method, Secure.class);
        // 权限表达式
        String secureExpression = secure.value();
        if (StrUtil.isNotBlank(secureExpression)) {
            // 方法参数值
            Object[] args = joinPoint.getArgs();
            StandardEvaluationContext context = getEvaluationContext(method, args);
            return SecureCheckUtil.checkExpression(secureExpression, context);
        }
        return false;
    }

    /**
     * 包含方法上的参数的上下文
     *
     * @param method 方法
     * @param args   参数
     * @return 上下文
     */
    private StandardEvaluationContext getEvaluationContext(Method method, Object[] args) {
        // 初始化Sp el表达式上下文，并设置 处理函数
        StandardEvaluationContext context = new StandardEvaluationContext(secureExpressionHandler);
        // 设置表达式支持spring bean
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));
        for (int i = 0; i < args.length; i++) {
            // 读取方法参数
            MethodParameter methodParam = ClassUtil.getMethodParameter(method, i);
            // 设置方法 参数名和值 为sp el变量
            context.setVariable(methodParam.getParameterName(), args[i]);
        }
        return context;
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
