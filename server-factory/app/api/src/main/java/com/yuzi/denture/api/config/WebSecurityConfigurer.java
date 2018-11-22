package com.yuzi.denture.api.config;


import com.yuzi.denture.api.session.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;


@Configuration
public class WebSecurityConfigurer {

    public static final String ExpiredUrl = "/denture/factory/info/expired";

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                HandlerInterceptor interceptor = new RequestInterceptor(ExpiredUrl);
                String[] includePatterns =  {"/**","/denture/factory/**"};
                String[] excludePatterns = {"/denture/factory/factoryUser/login", "/denture/factory/info/expired",
                        "/denture/factory/info/avatar/**", "/denture/factory/hj/**"};
                HandlerInterceptor mappedInterceptor = new MappedInterceptor(includePatterns, excludePatterns,
                        interceptor);
                registry.addInterceptor(mappedInterceptor);
            }
        };
    }
}
