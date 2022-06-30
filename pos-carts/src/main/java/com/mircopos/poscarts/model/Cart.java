package com.mircopos.poscarts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@Table("carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    static long Id = 1;
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
