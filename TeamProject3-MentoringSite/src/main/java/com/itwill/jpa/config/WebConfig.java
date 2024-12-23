package com.itwill.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:/C:/upload/");
//        registry.addResourceHandler("/mentor-profile-images/**")
//        .addResourceLocations("file:///C:/mentor-profile-images/");
//    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS 허용
//                .allowedOrigins("http://localhost:3000") // React 앱의 도메인
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
}
