package com.railway.sip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SipRccConverterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SipRccConverterApplication.class, args);
    }
}
