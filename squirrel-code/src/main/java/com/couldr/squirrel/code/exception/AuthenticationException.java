package com.couldr.squirrel.code.exception;

import org.springframework.http.HttpStatus;

/**
 * 认证异常
 *
 * @Author: iksen
 * @Date: 2020/4/6 18:58
 */
public class AuthenticationException extends SquirrelException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
