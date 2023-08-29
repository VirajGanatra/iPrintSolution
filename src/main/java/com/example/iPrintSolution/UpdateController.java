package com.example.iPrintSolution;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.sun.jna.Native;

import javax.servlet.http.HttpServletResponse;

public class UpdateController {

    @PostMapping("/update")
    public void updateAcc(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Account Details", required = true, content = @Content(schema = @Schema(implementation = Account.class))) @RequestBody Account temp, HttpServletResponse response) {

        AccDLL accDLL = (AccDLL) Native.load("Acc", AccDLL.class);
        int result = accDLL.update(temp.getAccName(), temp.getAccNo());
        if (result == 1) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
