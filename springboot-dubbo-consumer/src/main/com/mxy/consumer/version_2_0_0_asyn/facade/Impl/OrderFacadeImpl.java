package com.mxy.consumer.version_2_0_0_asyn.facade.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.google.common.collect.Lists;
import com.mxy.api.facade.GoodsFacade;
import com.mxy.api.facade.dto.GoodsDto;
import com.mxy.consumer.version_2_0_0_asyn.domain.Goods;
import com.mxy.consumer.version_2_0_0_asyn.domain.Order;
import com.mxy.consumer.version_2_0_0_asyn.facade.OrderFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderFacadeImpl implements OrderFacade {

    /**
     * 开启异步模式
     */
    @Reference(check = false, timeout = 4000, async = true, version = "2.0.0")
    private GoodsFacade goodsFacade;

    @Override
    public List<Order> getOrderListByOrderId(Long id) {

        List<GoodsDto> goodsDtoList = asyncCall(id);
        if (CollectionUtils.isEmpty(goodsDtoList)) {
            return Collections.emptyList();
        }

        return Lists.newArrayList(new Order(id, 100D, convert.apply(goodsDtoList)));
    }

    /**
     * dubbo 异步调用方式
     * @param id
     * @return
     */
    private List<GoodsDto> asyncCall(Long id) {
        Future<List<GoodsDto>> listFuture = RpcContext.getContext().asyncCall(new Callable<List<GoodsDto>>() {
            @Override
            public List<GoodsDto> call() throws Exception {
                return goodsFacade.getGoodsByOrderId(id);
            }
        });
        List<GoodsDto> goodsDtoList = null;

        try {
            goodsDtoList = listFuture.get();
        } catch (Exception e) {
            log.error(" asynch call throw Exception ",e);
        }
        return goodsDtoList;
    }

    private Function<List<GoodsDto>, List<Goods>> convert = goodsList ->
            goodsList.stream().map(c ->
                    new Goods(c.getGoodsName(), c.getGoodsColor(), c.getQutity())
            ).collect(Collectors.toList());

}
