package com.example.iPrintSolution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import javax.servlet.ServletContext;

import static com.example.iPrintSolution.SecurityConstants.SIGN_UP_URL;
import static com.example.iPrintSolution.SecurityConstants.LOGIN_URL;

@Configuration @EnableWebSecurity
public class WebSecurity{

    /*@Autowired
    ServletContext context;*/

    @Autowired
    private UserDetailsService userDetailsService;

    private ClientRepository clientRepository;

    public WebSecurity(UserDetailsService userService, ClientRepository clientRepository) {
        this.userDetailsService = userService;
        this.clientRepository = clientRepository;
    }

    /*@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }*/

    //@Bean @Primary
    /*public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //DefaultSecurityFilterChain chain = http.build();

//        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
//
//        AuthenticationManager authenticationManager = (AuthenticationManager) applicationContext.getBean("authenticationManager");



        //AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        http.cors().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(new JWTAuthenticationFilter(authenticationManager(http)), JWTAuthenticationFilter.class);

        return http.build();
    }*/

    AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ClientDetailsService CustomUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        System.out.println("filterChain");
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
