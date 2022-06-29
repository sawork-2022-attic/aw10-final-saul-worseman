package com.example.batch.repository;

import com.example.batch.model.AmazonProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<AmazonProduct, Long> {
    Optional<AmazonProduct> findByAsin(String Asin);
}
