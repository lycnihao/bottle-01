package run.bottle.app.code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Base exception
 *
 * @author iksen
 */
public abstract class BottleException extends RuntimeException {
    /**
     * Error errorData.
     */
    private Object errorData;

    public BottleException(String message) {
        super(message);
    }

    public BottleException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    @NonNull
    public BottleException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }

}
