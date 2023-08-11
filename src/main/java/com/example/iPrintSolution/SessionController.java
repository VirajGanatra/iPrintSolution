package com.example.iPrintSolution;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.parameters.RequestBody;



import javax.servlet.http.HttpServletRequest;

@RestController
public class SessionController {

    @Operation(summary = "Login to create session.", description = "Logs into API, reading credentials from database. This method is implemented by Spring Security.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful login", content = @Content(examples = {@ExampleObject(value = "Token for user testuser: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxMjUwNjQwNiwi")}, mediaType = "text/plain"))})
    @PostMapping("/session")
    public void theFakeLogin(@RequestBody(description = "User Credentials", required = true, content = @Content(schema = @Schema(implementation = Client.class))) Client c)
    {
        throw new IllegalStateException("This method should not be called. This method is implemented by Spring Security");
    }
}

