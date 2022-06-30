package com.mircopos.poscarts.integration;

import com.mircopos.poscarts.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Component;

@Component
public class HttpOutboundGateway {
    @Bean
    public IntegrationFlow outGate() {
        return IntegrationFlows.from("sampleChannel")
                .handle(Http.outboundGateway(m -> "http://localhost:8083/api/check/" + m.getPayload())
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(Order.class))
                .get();
    }
}