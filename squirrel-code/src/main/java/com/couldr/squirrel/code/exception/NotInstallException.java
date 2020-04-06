package com.couldr.squirrel.code.exception;

/**
 * 未安装引起的异常
 *
 * @author iksen
 */
public class NotInstallException extends BadRequestException {

    public NotInstallException(String message) {
        super(message);
    }

    public NotInstallException(String message, Throwable cause) {
        super(message, cause);
    }
}
