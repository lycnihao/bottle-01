package run.bottle.app.code.config;

import run.bottle.app.code.security.filter.AdminAuthenticationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static run.bottle.app.upload.model.FileConst.DELIMITER;
import static run.bottle.app.upload.model.FileConst.USER_HOME;

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

    private static final String FILE_PROTOCOL = "file:///";

    private final AdminAuthenticationInterceptor adminAuthenticationInterceptor;

    public WebMvcAutoConfiguration(AdminAuthenticationInterceptor adminAuthenticationInterceptor) {
        this.adminAuthenticationInterceptor = adminAuthenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAuthenticationInterceptor)
                .addPathPatterns("/**")
                .addPathPatterns("/api/**")
                .excludePathPatterns("/upload/**")
                .excludePathPatterns("/api/admin/login")
                .excludePathPatterns("/error");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String workDir = USER_HOME + DELIMITER + ".bottle" + DELIMITER;

        registry.addResourceHandler("upload/**")
                .addResourceLocations(FILE_PROTOCOL + workDir + "upload/");
    }
}
