package com.mxy.provide.version_2_0_0_asyn.domain;

import lombok.Data;

@Data
public class Goods {

    private String goodsName;
    private String goodsColor;
    private Integer Qutity;

    public Goods(String goodsName, String goodsColor, Integer qutity) {
        this.goodsName = goodsName;
        this.goodsColor = goodsColor;
        Qutity = qutity;
    }
}
