package com.example.iPrintSolution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.iPrintSolution.SecurityConstants.*;

@Configuration @EnableWebSecurity @EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurity{


    @Autowired
    private UserDetailsService userDetailsService;

    private ClientRepository clientRepository;

    public WebSecurity(UserDetailsService userService, ClientRepository clientRepository) {
        this.userDetailsService = userService;
        this.clientRepository = clientRepository;
    }


    AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ClientDetailsService CustomUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(CustomUserDetailService).passwordEncoder(bCryptPasswordEncoder);
        authenticationManager = authenticationManagerBuilder.build();


        http.csrf().disable().cors().disable().authorizeHttpRequests().antMatchers(LOGIN_URL, SIGN_UP_URL, "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**", "/v3/**", "/swagger-ui/**", "/update").permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationManager(authenticationManager)
                .addFilter(new JWTAuthenticationFilter(authenticationManager, clientRepository, bCryptPasswordEncoder))
                .addFilter(new JWTAuthorizationFilter(authenticationManager, clientRepository, bCryptPasswordEncoder))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }




}
