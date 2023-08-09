package com.example.iPrintSolution;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.iPrintSolution.SecurityConstants.EXPIRATION_TIME;
import static com.example.iPrintSolution.SecurityConstants.SECRET;

@CrossOrigin

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ClientRepository clientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, final ClientRepository clientRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        setFilterProcessesUrl("/session");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {


        try {
            Client temp = new ObjectMapper()
                    .readValue(req.getInputStream(), Client.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            temp.getUsername(),
                            temp.getPassword(),
                            new ArrayList<>())
            );
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        try {

            String token = JWT.create()
                    .withSubject(((CustomUserDetails) auth.getPrincipal()).getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET.getBytes()));

            String body = "Token for user " + ((CustomUserDetails) auth.getPrincipal()).getUsername() + ": " + token;

            res.getWriter().write(body);
            res.getWriter().flush();

            System.out.println("successfulAuthentication");

        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }




}
