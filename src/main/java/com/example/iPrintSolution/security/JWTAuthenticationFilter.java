package com.example.iPrintSolution.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.iPrintSolution.objects.client.Client;
import com.example.iPrintSolution.objects.client.CustomUserDetails;
import com.example.iPrintSolution.objects.client.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.example.iPrintSolution.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.iPrintSolution.security.SecurityConstants.SECRET;

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

            CustomUserDetails tempDTO= new CustomUserDetails(temp);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            temp.getUsername(),
                            temp.getPassword(),
                           tempDTO.getAuthorities())
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

        List<String> roles = Arrays.asList(((CustomUserDetails) auth.getPrincipal()).getRole());

        try {

            String token = JWT.create()
                    .withSubject(((CustomUserDetails) auth.getPrincipal()).getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .withClaim("roles", roles)
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
