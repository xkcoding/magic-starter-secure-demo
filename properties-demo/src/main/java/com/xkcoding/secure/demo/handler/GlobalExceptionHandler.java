package com.xkcoding.secure.demo.handler;

import com.xkcoding.magic.secure.exception.SecureException;
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
    @ExceptionHandler(SecureException.class)
    public String handlerSecureException(SecureException ex) {
        log.error("【拦截异常】", ex);
        return ex.getMessage();
    }
}
