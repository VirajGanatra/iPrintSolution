package com.example.iPrintSolution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer {

////    @Autowired
////    LoggingInterceptor loggingInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("addInterceptors");
//        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
//    }
//
//    @Bean
//    public SimpleUrlHandlerMapping simpleUrlHandlerMapping () {
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        Map<String, Object> map = new HashMap<>();
//        map.put("/app", httpRequestHandler());
//        mapping.setUrlMap(map);
//        mapping.setInterceptors(new Object[]{new MyInterceptor()});
//        return mapping;
//    }


}