package com.example.iPrintSolution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



//    @Bean
//    public static MappedInterceptor loggingInterceptor() {
//        return new MappedInterceptor(null, new LoggingInterceptor());
//    }

//    @Bean
//    public static LoggingInterceptor loggingInterceptor() {
//        return new LoggingInterceptor();
//    }

//    @Bean
//    public SimpleUrlHandlerMapping simpleUrlHandlerMapping () {
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        Map<String, Object> map = new HashMap<>();
//        map.put("/**", httpRequestHandler());
//        mapping.setUrlMap(map);
//        mapping.setInterceptors(new LoggingInterceptor());
//        return mapping;
//    }
//
//    @Bean
//    HttpRequestHandler httpRequestHandler () {
//        return new MyHttpRequestHandler();
//    }





}
