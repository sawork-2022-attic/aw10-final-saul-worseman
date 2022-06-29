package com.example.poscarts.repository;

import com.example.poscarts.model.Cart;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface CartRepository extends ReactiveCrudRepository<Cart, Long> {


}
