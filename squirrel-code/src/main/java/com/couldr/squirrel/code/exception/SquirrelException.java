package com.couldr.squirrel.code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Base exception
 *
 * @author iksen
 */
public abstract class SquirrelException extends RuntimeException {
    /**
     * Error errorData.
     */
    private Object errorData;

    public SquirrelException(String message) {
        super(message);
    }

    public SquirrelException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    @NonNull
    public SquirrelException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }

}
