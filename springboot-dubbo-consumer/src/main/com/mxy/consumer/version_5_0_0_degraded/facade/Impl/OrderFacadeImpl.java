package com.mxy.consumer.version_5_0_0_degraded.facade.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderFacadeImpl implements OrderFacade {


    /**
     * 1.mock=force:return+null 表示消费方对该服务的方法调用都直接返回 null 值，不发起远程调用。用来屏蔽不重要服务不可用时对调用方的影响。
     * 2.mock=fail:return+null 表示消费方对该服务的方法调用在失败后，再返回 null 值，不抛异常。用来容忍不重要服务不稳定时对调用方的影响。
     * 3.如果配置为true，则缺省使用mock类名，即类名+Mock后缀，但该类要和暴露的服务路径相同，没有这个类，项目启动时会报Class Not Found 错误
     */
    //第三种调用api包下的xxxMock方法 其他另种在当前版本2.6.2似乎有bug 返回依然报错
    @Reference(check = false, timeout = 4000, version = "5.0.0",mock ="true")
    private GoodsFacade goodsFacade;

    @Override
    public List<Order> getOrderListByOrderId(Long id) {
        List<GoodsDto> goodsDtoList  = goodsFacade.getGoodsByOrderId(id);
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
