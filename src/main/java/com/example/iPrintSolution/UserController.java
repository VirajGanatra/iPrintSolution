package com.example.iPrintSolution;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @PostMapping("/user")
    public void createUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User Credentials", required = true, content = @Content(schema = @Schema(implementation = Client.class))) @RequestBody HttpServletRequest req , HttpServletResponse res) throws IOException {
        try {
            Client temp = new ObjectMapper()
                    .readValue(req.getInputStream(), Client.class);

            System.out.println(bCryptPasswordEncoder.encode(temp.getPassword()));

            if (!(clientRepository.existsByUsername(temp.getUsername()))) {
                temp.setPassword(bCryptPasswordEncoder.encode(temp.getPassword()));
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
