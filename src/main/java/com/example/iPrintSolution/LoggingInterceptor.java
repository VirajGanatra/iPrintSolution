package com.example.iPrintSolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {

    private static Logger logger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getPathInfo().equals("/session")) {
            logger = LoggerFactory.getLogger("logreq");
        } else if (request.getPathInfo().equals("/printer")) {
            logger = LoggerFactory.getLogger("printreq");
        } else if (request.getPathInfo().equals("/user")) {
            logger = LoggerFactory.getLogger("regreq");
        } else {
            logger = LoggerFactory.getLogger("STDOUT");
        }

        logger.info("Received request: {} {} from {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (request.getPathInfo().equals("/session")) {
            logger = LoggerFactory.getLogger("logres");
        } else if (request.getPathInfo().equals("/printer")) {
            logger = LoggerFactory.getLogger("printres");
        } else if (request.getPathInfo().equals("/user")) {
            logger = LoggerFactory.getLogger("regres");
        } else {
            logger = LoggerFactory.getLogger("STDOUT");
        }


        logger.info("Sent response: {} {} with status {} and exception {}", request.getMethod(), request.getRequestURI(), response.getStatus(), ex.toString());
    }
}
