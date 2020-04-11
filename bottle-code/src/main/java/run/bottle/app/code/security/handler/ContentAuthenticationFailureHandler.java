package run.bottle.app.code.security.handler;

import run.bottle.app.code.exception.BottleException;
import run.bottle.app.code.exception.NotInstallException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Content authentication failure handler.
 *
 * @author iksen
 * @Date: 2020/4/6 18:43
 */
public class ContentAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, BottleException exception) throws IOException, ServletException {
        if (exception instanceof NotInstallException) {
            response.sendRedirect(request.getContextPath() + "/install");
            return;
        }

        // Forward to error
        request.getRequestDispatcher(request.getContextPath() + "/error").forward(request, response);
    }
}
