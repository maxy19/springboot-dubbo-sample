package com.mxy.api.facade;

import com.mxy.api.facade.dto.GoodsDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
@Slf4j
public class GoodsFacadeMock implements GoodsFacade {
    @Override
    public List<GoodsDto> getGoodsByOrderId(Long orderId) {
        log.info("开启服务降级,使用mock返回数据");
        return Collections.emptyList();
    }
}
