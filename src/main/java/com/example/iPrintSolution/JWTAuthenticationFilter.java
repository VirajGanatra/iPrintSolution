package com.example.iPrintSolution;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.iPrintSolution.SecurityConstants.EXPIRATION_TIME;
import static com.example.iPrintSolution.SecurityConstants.SECRET;


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
            //System.out.println(httpServletRequestToString(req));
            Client temp = new ObjectMapper()
                    .readValue(req.getInputStream(), Client.class);

            System.out.println(temp.toString());
//            System.out.println("temp");
//            System.out.println(temp.toString());
//
//            System.out.println(temp.getUsername());
//            List<Client> clients = clientRepository.findByUsername(temp.getUsername());


//            System.out.println(clients.toString());
//            for (Client client : clients) {
//                if (client.equals(temp)) {
//                    System.out.println("Client found");
//                } else {
//                    System.out.println("Client not found");
//                    System.out.println(client.toString());
//                }
//            }


//            String x = bCryptPasswordEncoder.encode("test");
//            System.out.println(x);
//            temp.setPassword(x);

            //System.out.println(auth.toString());
            //temp.setPassword(bCryptPasswordEncoder.encode(temp.getPassword()));
            //System.out.println(temp.toString());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(temp.getUsername(), temp.getPassword(), new ArrayList<>());

            System.out.println(token.toString());
            System.out.println(token.getCredentials());
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

            String body = ((CustomUserDetails) auth.getPrincipal()).getUsername() + " " + token;

            res.getWriter().write(body);
            res.getWriter().flush();

            System.out.println("successfulAuthentication");
            System.out.println(body);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }

    private String httpServletRequestToString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        sb.append("Request Method = [").append(request.getMethod()).append("], ");
        sb.append("Request URL Path = [").append(request.getRequestURL()).append("], ");

        String headers =
                Collections.list(request.getHeaderNames()).stream()
                        .map(headerName -> headerName + " : " + Collections.list(request.getHeaders(headerName)) )
                        .collect(Collectors.joining(", "));

        if (headers.isEmpty()) {
            sb.append("Request headers: NONE,");
        } else {
            sb.append("Request headers: [").append(headers).append("],");
        }

        String parameters =
                Collections.list(request.getParameterNames()).stream()
                        .map(p -> p + " : " + Arrays.asList( request.getParameterValues(p)) )
                        .collect(Collectors.joining(", "));

        if (parameters.isEmpty()) {
            sb.append("Request parameters: NONE.");
        } else {
            sb.append("Request parameters: [").append(parameters).append("].");
        }

        return sb.toString();
    }


}
