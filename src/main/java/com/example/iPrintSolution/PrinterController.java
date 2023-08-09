package com.example.iPrintSolution;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PrinterController {

    @Autowired
    PrinterRepository printerRepository;

     @PostMapping("/printer")
    public void createPrinter(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Printer temp = new ObjectMapper()
                .readValue(req.getInputStream(), Printer.class);
        printerRepository.save(temp);
     }

    @GetMapping("/printer")
    public void getPrinter(){
        //return webpage with registration form
    }
}
