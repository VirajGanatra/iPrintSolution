//package com.example.iPrintSolution;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class SessionController {
//    Client client = new Client();
//    ClientRepository ClientRepository;
//
//    @PostMapping("/session")
//    public Client login(@RequestBody String username, String password) {
//        Client client = ClientRepository.findByUsername(username);
//        if (client.getPassword().equals(password)) {
//            return client;
//        } else {
//            return null;
//        }
//
//    }
//
//    @GetMapping("/session")
//    public void getSession() {
//        //return webpage with login form
//    }
//}
