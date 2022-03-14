package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PaperManagementApplication  {

    public static void main(String[] args) {
        SpringApplication.run(PaperManagementApplication.class, args);
    }


}


/*
*
* extends SpringBootServletInitializer
*
* @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PaperManagementApplication.class);
    }
* */