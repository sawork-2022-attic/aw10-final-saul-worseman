package com.mircopos.posorder.model;


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
    private  double totalPrice;
    private String status;

    static long currentId = 0L;
    public  void  addToTotalPrice(double cost){
        totalPrice += cost;
    }
}
