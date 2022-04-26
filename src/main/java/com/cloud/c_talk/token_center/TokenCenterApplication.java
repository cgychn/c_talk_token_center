package com.cloud.c_talk.token_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TokenCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenCenterApplication.class, args);
    }

}
