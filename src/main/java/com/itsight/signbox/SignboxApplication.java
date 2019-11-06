package com.itsight.signbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SignboxApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SignboxApplication.class, args);
    }

}
