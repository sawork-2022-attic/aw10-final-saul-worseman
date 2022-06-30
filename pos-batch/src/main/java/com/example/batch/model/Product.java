package com.example.batch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private String image;

    public Product(AmazonProduct amazonProduct) {
        this.name = amazonProduct.getTitle();
        this.price = amazonProduct.getPrice().isEmpty() ? 0.0 : Double.parseDouble(amazonProduct.getPrice().substring(1));
        this.image = amazonProduct.getImageURLHighRes().isEmpty() ? null : amazonProduct.getImageURLHighRes().get(0);

    }


}
