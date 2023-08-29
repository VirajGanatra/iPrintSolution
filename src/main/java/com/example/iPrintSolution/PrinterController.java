package com.example.iPrintSolution;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;


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


    private WebClient localApiClient;

    @Autowired
    public void UserService(WebClient localApiClient) {
        this.localApiClient = localApiClient;
    }

    @Operation(summary = "Print account details", description = "Prints account details found in json-server database.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful printing"), @ApiResponse(responseCode = "403", description = "Access forbidden")})
    @SecurityRequirement(name = "JWT Auth")
    @Secured("PRINT")
    @PostMapping("/print")
    public void printDetails(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Account Number", required = true, content = @Content(examples = @ExampleObject(name = "Valid", value = " {\"accountNumber\": \"0100000009999\"}", description = "Valid account number"))) @RequestBody String body, HttpServletResponse res) throws IOException {
        try {
            String result = localApiClient.post().uri("http://localhost:3001/account").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).bodyValue(body).retrieve().bodyToMono(String.class).block();
            assert result != null;
            res.getWriter().write(result);
        } catch (Exception e) {
            System.out.println("Error printing details");
            e.printStackTrace();
        }
    }
}
