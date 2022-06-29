package com.example.poscarts.rest;


import com.example.poscarts.model.Cart;
import com.example.poscarts.model.Item;
import com.example.poscarts.repository.CartRepository;
import com.example.poscarts.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create")
    public Mono<ResponseEntity<Cart>> createCart(){
        return cartService.createCart()
                .switchIfEmpty(Mono.error(new Exception("failed")))
                .map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                .onErrorResume(e -> Mono.just(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)));
    }

    @PostMapping("/add/{CartId}")
    public Mono<ResponseEntity<Flux<Item>>> addItem(@PathVariable Long CartId, @RequestBody Item item){
        item.setCartId(CartId);
        item.setItemId(null);
        System.out.println(item);
        return Mono.just(new ResponseEntity<>(cartService.addItem(item), HttpStatus.OK));
    }

}
