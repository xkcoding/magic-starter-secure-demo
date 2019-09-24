package com.xkcoding.secure.demo.handler;

import com.xkcoding.magic.secure.exception.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthorizationException.class)
    public String handlerAuthorizationException(AuthorizationException ex) {
        log.error("【拦截异常】无权限异常", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public String handlerExpiredTokenException(ExpiredTokenException ex) {
        log.error("【拦截异常】Token 过期异常", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidTokenException.class)
    public String handlerInvalidTokenException(InvalidTokenException ex) {
        log.error("【拦截异常】Token 格式异常", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(NotFoundTokenException.class)
    public String handlerNotFoundTokenException(NotFoundTokenException ex) {
        log.error("【拦截异常】Token 未找到异常", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(SecureException.class)
    public String handlerNotFoundTokenException(SecureException ex) {
        log.error("【拦截异常】", ex);
        return ex.getMessage();
    }
}
