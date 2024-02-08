package com.teach.java_grading.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/search")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET")
                .allowCredentials(true);

        registry.addMapping("/api/calculate")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET")
                .allowCredentials(true);

        registry.addMapping("/login")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("POST")
                .allowCredentials(true);
    }
}
