package com.couldr.squirrel.code.exception;

import org.springframework.http.HttpStatus;

/**
 * 服务引起的异常
 *
 * @author iksen
 */
public class ServiceException extends SquirrelException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
