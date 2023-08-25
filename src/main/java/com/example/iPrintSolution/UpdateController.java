package com.example.iPrintSolution;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.sun.jna.Native;

import javax.servlet.http.HttpServletResponse;

public class UpdateController {

    @PostMapping("/update")
    public void updateAcc(@RequestBody String json, HttpServletResponse response) {
        System.out.println(json);
        String[] parts = json.split(",");
        String username = parts[0].split(":")[1];
        username = username.substring(1, username.length() - 1);
        String number = parts[1].split(":")[1];
        number = number.substring(1, number.length() - 1);
        int acno = Integer.parseInt(number);
        AccDLL accDLL = (AccDLL) Native.load("Acc", AccDLL.class);
        int result = accDLL.update(username, acno);
        if (result == 1) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
