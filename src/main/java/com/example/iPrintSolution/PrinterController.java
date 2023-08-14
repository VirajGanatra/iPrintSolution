package com.example.iPrintSolution;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin
@RestController
public class PrinterController {

    @Autowired
    PrinterRepository printerRepository;

    @Operation(summary = "Create a printer", description = "Adds printer to database.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful printer creation"), @ApiResponse(responseCode = "403", description = "Access forbidden")})
    @SecurityRequirement(name = "JWT Auth")
    @Secured({"ROLE_USER", "ROLE_SUPERADMIN"})
    @PostMapping("/printer")
    public void createPrinter(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Printer Details", required = true, content = @Content(schema = @Schema(implementation = Printer.class))) @RequestBody Printer temp, HttpServletResponse res) throws IOException {

         try {
             printerRepository.save(temp);
         } catch (Exception e) {
             System.out.println("Error creating printer");
             e.printStackTrace();
         }

    }

//    @GetMapping("/printer")
//    public void getPrinter(){
//        //return webpage with registration form
//    }
}
