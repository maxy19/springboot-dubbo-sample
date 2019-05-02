package com.mxy.consumer.version_5_0_0_degraded.domain;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private Long id;
    private Double OrderPrice;
    private List<Goods> goodsList;

    public Order() {
    }

    public Order(Long id, Double orderPrice, List<Goods> goodsList) {
        this.id = id;
        OrderPrice = orderPrice;
        this.goodsList = goodsList;
    }
}
