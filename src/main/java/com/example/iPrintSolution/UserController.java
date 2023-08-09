package com.example.iPrintSolution;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {


    //private Client client = new Client();
    @Autowired
    ClientRepository clientRepository;

     @Autowired
     private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/user")
    public void createUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            Client temp = new ObjectMapper()
                    .readValue(req.getInputStream(), Client.class);

            System.out.println(temp.toString());
            System.out.println(temp.getPassword());
            System.out.println(bCryptPasswordEncoder.encode(temp.getPassword()));

            if (!(clientRepository.existsByUsername(temp.getUsername()))) {
                temp.setPassword(bCryptPasswordEncoder.encode(temp.getPassword()));
                System.out.println(temp.toString());
                clientRepository.save(temp);
                System.out.println("Client saved");
            } else {
                System.out.println("Client already exists");
            }
        } catch (Exception e) {
            System.out.println("Error creating user");
            e.printStackTrace();
        }

        System.out.println("create user");
    }

    @GetMapping("/user")
    public void getUser() {
        System.out.println("get user");
    }
}
