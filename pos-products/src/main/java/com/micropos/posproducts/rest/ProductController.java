package com.micropos.posproducts.rest;

import com.micropos.posproducts.model.Product;
import com.micropos.posproducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.channels.FileLock;

@RestController
@RequestMapping("/api")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/{productId}")
    public Mono<ResponseEntity<Product>> getProductById(
            @PathVariable("productId") Long productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new Exception("not found")))
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .onErrorResume(e -> Mono.just(new ResponseEntity<>(null, HttpStatus.NOT_FOUND)));

    }


    @GetMapping("/products")
    public Mono<ResponseEntity<Flux<Product>>> getProducts() {
        return Mono.just(new ResponseEntity<>(productRepository
                .findFirstPage()
                .filter(e -> e.getPrice() != 0), HttpStatus.OK));
    }

    @GetMapping("/products/{offset}/{limit}")
    public Mono<ResponseEntity<Flux<Product>>> getProductsPage(@PathVariable Long offset, @PathVariable Long limit) {
        return Mono.just(new ResponseEntity<>(productRepository
                .findPage(offset, limit)
                .filter(e -> e.getPrice() != 0), HttpStatus.OK));
    }

//    @GetMapping("/products/{productName}")
//    public Mono<ResponseEntity<Product>> getProductByName(
//            @PathVariable("productName") String productName){
//        return productRepository.findByName(productName)
//                .switchIfEmpty(Mono.error(new Exception("not found")))
//                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
//                .onErrorResume(e -> Mono.just(new ResponseEntity<>(null, HttpStatus.NOT_FOUND)));
//    }


}
