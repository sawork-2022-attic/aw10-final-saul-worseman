package com.mircopos.posorder.service;

import com.mircopos.posorder.model.Item;
import com.mircopos.posorder.model.Order;
import com.mircopos.posorder.model.Product;
import com.mircopos.posorder.repository.ItemRepository;
import com.mircopos.posorder.repository.OrderRepository;
import com.mircopos.posorder.repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemRepository itemRepository;


    public void createNewOrder(Long cartId) {
        Flux<Item> itemFlux = itemRepository.findByCartId(cartId);
        List<Item> itemList = itemFlux.collectList().block();
        Order order = new Order();
        order.setOrderId(cartId);
        order.setCartId(cartId);
        order.setStatus("not paid");
        for(var i: itemList){
            order.addToTotalPrice(calculateItemCost(i));
        }
        orderRepository.save(order).subscribe();
    }

    public double calculateItemCost(Item item){
        return productRepository.findById(item.getProductId())
                .onErrorReturn(new Product())
                .block()
                .getPrice() * item.getQuantity();
    }

    public Mono<Order> getOrderByCartId(Long cartId){
        return orderRepository.findById(cartId);
    }


}
