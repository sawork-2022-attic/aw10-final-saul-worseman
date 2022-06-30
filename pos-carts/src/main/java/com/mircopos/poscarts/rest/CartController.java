package com.mircopos.poscarts.rest;


import com.mircopos.poscarts.model.Cart;
import com.mircopos.poscarts.model.Item;
import com.mircopos.poscarts.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private StreamBridge streamBridge;

    @Value("${output.channel}")
    private String bindName;

    @PostMapping("/carts")
    public Mono<ResponseEntity<Cart>> createCart(){
        System.out.println("received");
        return cartService.createCart()
                .switchIfEmpty(Mono.error(new Exception("failed")))
                .map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                .onErrorResume(e -> Mono.just(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)));
    }

    @PostMapping("/carts/{CartId}")
    public Mono<ResponseEntity<Flux<Item>>> addItem(@PathVariable Long CartId, @RequestBody Item item){
        item.setCartId(CartId);
        item.setItemId();
        return Mono.just(new ResponseEntity<>(cartService.addItem(item), HttpStatus.OK));
    }

    @PostMapping("/carts/checkout/{cartId}")
    public Mono<ResponseEntity<Void>> checkout(@PathVariable Long cartId){
        streamBridge.send(bindName, cartId);
        return Mono.just(new ResponseEntity<>(HttpStatus.OK));
    }
}
