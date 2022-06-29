package com.micropos.carts.integration;

import com.micropos.carts.dto.OrderDto;
import com.micropos.carts.model.Joke;
import com.micropos.carts.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.http.dsl.HttpMessageHandlerSpec;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.jar.JarOutputStream;

@Component
public class HttpOutboundGateway {
    @Bean
    public IntegrationFlow outGate() {
        return IntegrationFlows.from("sampleChannel")
                .handle(Http.outboundGateway(m -> "http://localhost:8085/api/delivery/check/" + m.getPayload())
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(Order.class))
                .get();
    }
}