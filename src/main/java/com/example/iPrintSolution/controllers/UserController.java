package com.example.iPrintSolution.controllers;

import com.example.iPrintSolution.objects.client.Client;
import com.example.iPrintSolution.objects.client.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
public class UserController {



    @Autowired
    ClientRepository clientRepository;

     @Autowired
     private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Operation(summary = "Create a user", description = "Adds user to database. Password is stored in the database as a hash.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful registration")})
    @SecurityRequirement(name = "JWT Auth")
    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @PostMapping("/user")
    public void createUser(@RequestBody(description = "User Credentials", required = true, content = @Content(schema = @Schema(implementation = Client.class))) @org.springframework.web.bind.annotation.RequestBody Client temp, HttpServletResponse res) throws IOException {
        try {

            if (!(clientRepository.existsByUsername(temp.getUsername()))) {
                temp.setPassword(bCryptPasswordEncoder.encode(temp.getPassword()));
                clientRepository.save(temp);
                System.out.println("Client saved");
            } else {
                System.out.println("Client already exists");
            }
        } catch (Exception e) {
            System.out.println("Error creating user");
            e.printStackTrace(System.out);
        }

        System.out.println("create user");
    }

//    @GetMapping("/user")
//    public void getUser() {
//        System.out.println("get user");
//    }
}
