package com.xkcoding.spelaopdemo.test.handler;

import com.xkcoding.spelaopdemo.exception.SecureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 异常拦截
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/19 15:48
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SecureException.class)
    public String handlerSecureException(SecureException ex) {
        return ex.getMessage();
    }
}
