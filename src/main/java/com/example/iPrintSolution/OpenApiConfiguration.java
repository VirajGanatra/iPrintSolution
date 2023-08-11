package com.example.iPrintSolution;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;


@Configuration
@OpenAPIDefinition(info = @Info(title = "Spring Boot WebFlux Example", version = "0.0.1"))
@SecurityScheme(
        name = "JWT Auth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfiguration {
    static{
        SpringDocUtils.getConfig().removeRequestWrapperToIgnore(HttpServletRequest.class);
    }
}
