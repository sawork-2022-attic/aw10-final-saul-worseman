package com.example.poscarts.service;

import com.example.poscarts.model.Cart;
import com.example.poscarts.model.Item;
import com.example.poscarts.repository.CartRepository;
import com.example.poscarts.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;


    public Mono<Cart> createCart(){
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Flux<Item> addItem(Item item){
//          return itemRepository.save(item).log();
          return itemRepository.findByCartId(item.getCartId());
//        Cart cart = new Cart(item.getCartId());
//        Flux<Item> itemFlux = itemRepository.findItemsByCartId(item.getCartId());
//        return Mono.just(cart);
    }
}
