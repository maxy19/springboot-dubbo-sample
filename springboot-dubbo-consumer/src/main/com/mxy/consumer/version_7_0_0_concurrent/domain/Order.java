package com.mxy.consumer.version_7_0_0_concurrent.domain;

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
