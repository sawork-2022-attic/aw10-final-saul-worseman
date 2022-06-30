package com.mircopos.poscarts.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("orders")
public class Order {
    private Long cartId;
    @Id
    private Long orderId;
    private volatile double totalPrice;
    private String status;

    static long currentId = 0L;
    public synchronized void  addToTotalPrice(double cost){
        totalPrice += cost;
    }
}
