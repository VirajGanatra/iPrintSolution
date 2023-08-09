package com.example.iPrintSolution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Client> users = clientRepository.findByUsername(username);

        if (users == null)
            throw new UsernameNotFoundException("Bad credentials");

        Client user = users.get(0);

        return new CustomUserDetails(user);
    }

}
