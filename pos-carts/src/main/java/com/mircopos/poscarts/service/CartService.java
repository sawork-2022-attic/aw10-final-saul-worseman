package com.mircopos.poscarts.service;

import com.mircopos.poscarts.model.Cart;
import com.mircopos.poscarts.model.Item;
import com.mircopos.poscarts.repository.CartRepository;
import com.mircopos.poscarts.repository.ItemRepository;
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


    public Mono<Cart> createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Flux<Item> addItem(Item item) {
        itemRepository.save(item).subscribe();
        return itemRepository.findByCartId(item.getCartId());
    }
}
