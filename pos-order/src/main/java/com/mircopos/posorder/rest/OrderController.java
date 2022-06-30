package com.mircopos.posorder.rest;

import com.mircopos.posorder.model.Order;
import com.mircopos.posorder.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;


    public static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Bean
    public Consumer<Long> checkorder() {
        return cartId->{
            log.info("received a new message");
            orderService.createNewOrder(cartId);
        };
    }

    @GetMapping("/check/{cartId}")
    public Mono<ResponseEntity<Order>> getOrderByCartId(@PathVariable Long cartId){
        return orderService.getOrderByCartId(cartId)
                .switchIfEmpty(Mono.error(new Exception("not found")))
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .onErrorResume(e -> Mono.just(new ResponseEntity<>(null, HttpStatus.NOT_FOUND)));
    }
}
