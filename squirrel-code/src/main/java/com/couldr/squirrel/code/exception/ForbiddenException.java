package com.couldr.squirrel.code.exception;

import org.springframework.http.HttpStatus;

/**
 * 禁止访问异常
 *
 * @author iksen
 */
public class ForbiddenException extends SquirrelException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
