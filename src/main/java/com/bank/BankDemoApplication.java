package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BankDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankDemoApplication.class, args);
    }

}
