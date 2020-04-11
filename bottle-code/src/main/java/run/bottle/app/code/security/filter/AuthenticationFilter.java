package run.bottle.app.code.security.filter;

import run.bottle.app.cache.LocalCache;
import run.bottle.app.code.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static run.bottle.app.code.model.support.BottleConst.API_ACCESS_KEY_HEADER_NAME;

/**
 * 身份验证过滤器
 *
 * @Author: iksen
 * @Date: 2020/4/6 18:43
 */
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private final LocalCache localCache;
    private final UserService userService;

    public AuthenticationFilter(LocalCache localCache, UserService userService) {
        this.localCache = localCache;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*String token = getTokenFromRequest(request);

        if (StringUtils.isBlank(token)) {
            new DefaultAuthenticationFailureHandler().onFailure(request, response, new AuthenticationException("未登录，请登陆后访问"));
            return;
        }

        // 从缓存中获取用户ID
        Object object = localCache.get(SecurityUtils.buildTokenAccessKey(token));
        if (ObjectUtils.isEmpty(object)){
            new DefaultAuthenticationFailureHandler().onFailure(request, response, new AuthenticationException("Token 已过期或不存在").setErrorData(token));
        }

        // 获取用户
        User user = userService.getById(Integer.valueOf(object.toString()));

        //保存用户信息
        SecurityContextHolder.setContext(new UserDetail(user));

        filterChain.doFilter(request, response);*/
    }

    protected String getTokenFromRequest(HttpServletRequest request) {
        // Get from header
        String accessKey = request.getHeader(API_ACCESS_KEY_HEADER_NAME);

        // Get from param
        if (StringUtils.isBlank(accessKey)) {
            accessKey = request.getParameter(API_ACCESS_KEY_HEADER_NAME);

            log.debug("从参数获得访问密钥: [{}: {}]", API_ACCESS_KEY_HEADER_NAME, accessKey);
        } else {
            log.debug("从标题获得访问密钥: [{}: {}]", API_ACCESS_KEY_HEADER_NAME, accessKey);
        }

        return accessKey;
    }
}
