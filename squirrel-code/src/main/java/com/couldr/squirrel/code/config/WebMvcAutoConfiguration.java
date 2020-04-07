package com.couldr.squirrel.code.config;

import com.couldr.squirrel.code.security.filter.AdminAuthenticationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Mvc configuration.
 *
 * @Author: iksen
 * @Date: 2020/4/6 21:42
 */
@Slf4j
@Configuration
@EnableWebMvc
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    private final AdminAuthenticationInterceptor adminAuthenticationInterceptor;

    public WebMvcAutoConfiguration(AdminAuthenticationInterceptor adminAuthenticationInterceptor) {
        this.adminAuthenticationInterceptor = adminAuthenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAuthenticationInterceptor)
                .addPathPatterns("/**")
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/admin/login")
                .excludePathPatterns("/error");
    }
}
