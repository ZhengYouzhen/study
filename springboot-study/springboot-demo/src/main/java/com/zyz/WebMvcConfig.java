package com.zyz;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * spring的自定义配置文件，spring5推荐直接使用WebMvcConfigurer
 *
 * @author zyz
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域CORS配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//		路径
        registry.addMapping("/cors/**")
//				允许的头部信息
                .allowedHeaders("*")
//				允许的请求
                .allowedMethods("POST", "GET")
                .allowedOrigins("*");
    }


}
