package com.example.poscarts.model;

import lombok.*;

import javax.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("items")
public class Item {
    static long itemNum = 0;

    @Id
    private Long itemId;

    private Long cartId;

    private Long productId;

    private int quantity;


    public void setItemId(){
        this.itemId = itemNum;
        itemNum++;
    }

    public Item(long productId, int quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

}
