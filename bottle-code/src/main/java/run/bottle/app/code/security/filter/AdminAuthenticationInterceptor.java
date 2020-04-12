package run.bottle.app.code.security.filter;

import run.bottle.app.cache.LocalCache;
import run.bottle.app.code.exception.AuthenticationException;
import run.bottle.app.code.model.entity.User;
import run.bottle.app.code.model.support.UserDetail;
import run.bottle.app.code.security.context.SecurityContextHolder;
import run.bottle.app.code.security.handler.DefaultAuthenticationFailureHandler;
import run.bottle.app.code.security.util.SecurityUtils;
import run.bottle.app.code.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static run.bottle.app.code.model.support.BottleConst.API_ACCESS_KEY_HEADER_NAME;

/**
 * 身份验证过滤器
 *
 * @Author: iksen
 * @Date: 2020/4/6 21:32
 */
@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {

    private final LocalCache localCache;

    public AdminAuthenticationInterceptor(LocalCache localCache, UserService userService) {
        this.localCache = localCache;
        this.userService = userService;
    }

    private final UserService userService;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = getTokenFromRequest(request);
        if (StringUtils.isBlank(token)) {
            new DefaultAuthenticationFailureHandler().onFailure(request, response, new AuthenticationException("未登录，请登陆后访问"));
            return false;
        }

        // 从缓存中获取用户ID
        Object object = localCache.get(SecurityUtils.buildTokenAccessKey(token));
        if (ObjectUtils.isEmpty(object)){
            new DefaultAuthenticationFailureHandler().onFailure(request, response, new AuthenticationException("Token 已过期或不存在").setErrorData(token));
            return false;
        }

        // 获取用户
        User user = userService.getById(Integer.valueOf(object.toString()));
        System.out.println(user);
        //保存用户信息
        SecurityContextHolder.setContext(new UserDetail(user));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

    protected String getTokenFromRequest(HttpServletRequest request) {
        // Get from header
        String accessKey = request.getHeader(API_ACCESS_KEY_HEADER_NAME);

        // Get from param
        if (StringUtils.isBlank(accessKey)) {
            accessKey = request.getParameter(API_ACCESS_KEY_HEADER_NAME);
            System.out.println(accessKey);
        } else {
            System.out.println(accessKey);
        }

        return accessKey;
    }
}
