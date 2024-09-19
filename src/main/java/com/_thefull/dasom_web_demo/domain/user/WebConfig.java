package com._thefull.dasom_web_demo.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // "/api/promotion-discount/**" 경로에 대해 인터셉터 적용
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/promotion-discount/**")
                .addPathPatterns("/settings/dasom-locations/**")
                .excludePathPatterns("/page/user/login", "/page/user/register"); // 로그인, 회원가입 페이지는 제외
    }
}
