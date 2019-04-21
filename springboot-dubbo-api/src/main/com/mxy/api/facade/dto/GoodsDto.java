package com.mxy.api.facade.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsDto implements Serializable {

    private String goodsName;
    private String goodsColor;
    private Integer Qutity;

    public GoodsDto() {
    }

    public GoodsDto(String goodsName, String goodsColor, Integer qutity) {
        this.goodsName = goodsName;
        this.goodsColor = goodsColor;
        Qutity = qutity;
    }
}
