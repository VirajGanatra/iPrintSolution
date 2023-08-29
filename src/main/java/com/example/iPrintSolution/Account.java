package com.example.iPrintSolution;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;


@Schema(
        description = "Account schema",
        type = "Account",
        example = " {\"accName\" :\"name\" ,"
                + "\"accNo\": \"1234\"}"
)
public class Account {
    String accName;
    int accNo;

    public Account(String accName, int accNo) {
        this.accName = accName;
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }


    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }
}
