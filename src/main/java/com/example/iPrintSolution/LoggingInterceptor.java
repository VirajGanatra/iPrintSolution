package com.example.iPrintSolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger("STDOUT");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");

        if (request.getServletPath().equals("/session")) {
            logger = LoggerFactory.getLogger("logreq");
        } else if (request.getServletPath().equals("/printer")) {
            logger = LoggerFactory.getLogger("printreq");
        } else if (request.getServletPath().equals("/user")) {
            logger = LoggerFactory.getLogger("regreq");
        } else {
            logger = LoggerFactory.getLogger("STDOUT");}

        logger.info("Received request: {} {} from {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");

        if (request.getServletPath().equals("/session")) {
            logger = LoggerFactory.getLogger("logres");
        } else if (request.getServletPath().equals("/printer")) {
            logger = LoggerFactory.getLogger("printres");
        } else if (request.getServletPath().equals("/user")) {
            logger = LoggerFactory.getLogger("regres");
        } else {
            logger = LoggerFactory.getLogger("STDOUT");
        }


        logger.info("Sent response: {} {} with status {} and exception {}", request.getMethod(), request.getRequestURI(), response.getStatus(), ex.toString());
    }
}
