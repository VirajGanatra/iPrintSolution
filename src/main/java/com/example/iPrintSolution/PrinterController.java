package com.example.iPrintSolution;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

public class PrinterController {
    Printer printer = new Printer();
    PrinterRepository PrinterRepository;
     @PostMapping("/printer")
    public void createPrinter(String name, String ip){
        printer.setName(name);
        printer.setIp(ip);
     }

    @GetMapping("/printer")
    public void getPrinter(){
        //return webpage with registration form
    }
}
