package com.xkcoding.spelaopdemo.exception;

/**
 * <p>
 * 鉴权异常
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 16:03
 */
public class SecureException extends RuntimeException {
    public SecureException(Throwable cause) {
        super(cause);
    }

    public SecureException(String message) {
        super(message);
    }

    public SecureException(String message, Throwable cause) {
        super(message, cause);
    }
}
