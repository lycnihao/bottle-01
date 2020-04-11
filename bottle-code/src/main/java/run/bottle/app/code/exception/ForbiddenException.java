package run.bottle.app.code.exception;

import org.springframework.http.HttpStatus;

/**
 * 禁止访问异常
 *
 * @author iksen
 */
public class ForbiddenException extends BottleException {

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
