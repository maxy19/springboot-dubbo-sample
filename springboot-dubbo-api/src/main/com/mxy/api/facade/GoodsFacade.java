package com.mxy.api.facade;

import com.mxy.api.facade.dto.GoodsDto;

import java.util.List;

public interface GoodsFacade {

    List<GoodsDto> getGoodsByOrderId(Long orderId);

}