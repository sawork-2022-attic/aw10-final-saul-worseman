package com.example.poscarts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@Table("carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    static long Id = 0;
    private Long cartId;

    @ReadOnlyProperty
    @Transient
    private List<Item> items;

    public Cart(){
        cartId = Id;
        Id++;
    }

    public Cart(Long cartId){
        this.cartId = cartId;
    }


}
