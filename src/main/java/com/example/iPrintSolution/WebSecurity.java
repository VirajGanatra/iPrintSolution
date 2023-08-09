package com.example.iPrintSolution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.iPrintSolution.SecurityConstants.SIGN_UP_URL;
import static com.example.iPrintSolution.SecurityConstants.LOGIN_URL;

@Configuration @EnableWebSecurity
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


        http.csrf().disable().cors().disable().authorizeHttpRequests().antMatchers(SIGN_UP_URL, LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationManager(authenticationManager)
                .addFilter(new JWTAuthenticationFilter(authenticationManager, clientRepository, bCryptPasswordEncoder))
                .addFilter(new JWTAuthorizationFilter(authenticationManager, clientRepository, bCryptPasswordEncoder))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }




}
