package com.couldr.squirrel.code.exception;

import org.springframework.http.HttpStatus;

/**
 * 错误请求引起的异常
 *
 * @author iksen
 */
public class BadRequestException extends SquirrelException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
