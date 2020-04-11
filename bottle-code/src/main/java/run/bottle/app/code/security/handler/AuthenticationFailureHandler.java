package run.bottle.app.code.security.handler;

import run.bottle.app.code.exception.BottleException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证失败处理程序。
 *
 * @author iksen
 */
public interface AuthenticationFailureHandler {

    /**
     * 在未成功验证用户时调用。
     *
     * @param request   http servlet request
     * @param response  http servlet response
     * @param exception api exception
     * @throws IOException      io exception
     * @throws ServletException service exception
     */
    void onFailure(HttpServletRequest request, HttpServletResponse response, BottleException exception) throws IOException, ServletException;
}
