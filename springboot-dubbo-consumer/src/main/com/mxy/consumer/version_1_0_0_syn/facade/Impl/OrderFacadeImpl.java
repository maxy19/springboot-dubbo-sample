package com.mxy.consumer.version_1_0_0_syn.facade.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.mxy.api.facade.GoodsFacade;
import com.mxy.api.facade.dto.GoodsDto;
import com.mxy.consumer.version_1_0_0_syn.domain.Goods;
import com.mxy.consumer.version_1_0_0_syn.domain.Order;
import com.mxy.consumer.version_1_0_0_syn.facade.OrderFacade;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderFacadeImpl implements OrderFacade {

    @Reference(check = false, timeout = 4000,version = "1.0.0")
    private GoodsFacade goodsFacade;

    @Override
    public List<Order> getOrderListByOrderId(Long id) {
        List<GoodsDto> goodsDtoList = goodsFacade.getGoodsByOrderId(id);
        if (CollectionUtils.isEmpty(goodsDtoList)) {
            return Collections.EMPTY_LIST;
        }
        return Lists.newArrayList(new Order(id, 100D, convert.apply(goodsDtoList)));
    }

    private Function<List<GoodsDto>, List<Goods>> convert = (goodsList) ->
            goodsList.stream().map(c ->
                    new Goods(c.getGoodsName(), c.getGoodsColor(), c.getQutity())
            ).collect(Collectors.toList());

}
