package com.mircopos.posorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;

@SpringBootApplication
@EnableEurekaClient
public class PosOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosOrderApplication.class, args);
    }

    @Bean
    public DirectChannel sampleChannel() {
        return new DirectChannel();
    }

}
