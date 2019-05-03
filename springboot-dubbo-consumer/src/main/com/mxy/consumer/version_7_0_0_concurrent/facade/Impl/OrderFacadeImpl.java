package com.mxy.consumer.version_7_0_0_concurrent.facade.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.mxy.api.facade.GoodsFacade;
import com.mxy.api.facade.dto.GoodsDto;
import com.mxy.consumer.version_7_0_0_concurrent.domain.Goods;
import com.mxy.consumer.version_7_0_0_concurrent.domain.Order;
import com.mxy.consumer.version_7_0_0_concurrent.facade.OrderFacade;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderFacadeImpl implements OrderFacade {

    @Reference(check = false, timeout = 4000,version = "7.0.0")
    private GoodsFacade goodsFacade;

    @Override
    public List<Order> getOrderListByOrderId(Long id) {
        long s1 = System.currentTimeMillis();
        List<GoodsDto> goodsDtoList = goodsFacade.getGoodsByOrderId(id);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long s2 = System.currentTimeMillis();
        System.out.println("take = "+(s2-s1));
        if (CollectionUtils.isEmpty(goodsDtoList)) {
            return Collections.emptyList();
        }
        return Lists.newArrayList(new Order(id, 100D, convert.apply(goodsDtoList)));
    }

    private Function<List<GoodsDto>, List<Goods>> convert = goodsList ->
            goodsList.stream().map(c ->
                    new Goods(c.getGoodsName(), c.getGoodsColor(), c.getQutity())
            ).collect(Collectors.toList());

}
