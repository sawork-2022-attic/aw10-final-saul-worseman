package com.mircopos.poscarts.repository;

import com.mircopos.poscarts.model.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends ReactiveCrudRepository<Cart, Long> {


}
