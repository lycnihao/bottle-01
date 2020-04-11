package run.bottle.app.code.exception;

import org.springframework.http.HttpStatus;

/**
 * 认证异常
 *
 * @Author: iksen
 * @Date: 2020/4/6 18:58
 */
public class AuthenticationException extends BottleException {

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
