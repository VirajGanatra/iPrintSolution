package com.example.iPrintSolution;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {
    private Client client = new Client();
    ClientRepository clientRepository;

    @PostMapping("/user")
    public void createUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) throws IOException {
        client.setUsername(username);
        client.setPassword(password);
        clientRepository.save(client);
        response.sendRedirect("/session");
    }

    @GetMapping("/user")
    public void getUser() {
        //return webpage with registration form
    }
}
