package com.micropos.posproducts.repository;

import com.micropos.posproducts.model.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Mono<Product> findById(Long productId);

    @Query(value = "select * " +
            "from products " +
            "order by id " +
            "limit 30")
    Flux<Product> findFirstPage();

    Flux<Product> findAll();

    Mono<Product> findByName(String ProductName);

    @Query(value = "SELECT * FROM products " +
            "order by id " +
            "LIMIT :limit OFFSET :offset")
    Flux<Product> findPage(Long offset, Long limit);
}