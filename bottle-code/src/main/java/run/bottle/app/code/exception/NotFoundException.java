package run.bottle.app.code.exception;

import org.springframework.http.HttpStatus;

/**
 * 找不到实体异常
 *
 * @author iksen
 */
public class NotFoundException extends BottleException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
