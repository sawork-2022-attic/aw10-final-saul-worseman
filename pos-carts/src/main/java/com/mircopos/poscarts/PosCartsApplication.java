package com.mircopos.poscarts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PosCartsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosCartsApplication.class, args);
    }

}
