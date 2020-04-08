package com.couldr.squirrel.upload.exception;

/**
 * 文件操作异常
 *
 * @author iksen
 */
public class FileOperationException extends ServiceException {
    public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
