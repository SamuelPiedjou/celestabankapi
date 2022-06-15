package com.celestabank.celestabankapi;

import com.celestabank.celestabankapi.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class CelestabankapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CelestabankapiApplication.class, args);
    }

}
