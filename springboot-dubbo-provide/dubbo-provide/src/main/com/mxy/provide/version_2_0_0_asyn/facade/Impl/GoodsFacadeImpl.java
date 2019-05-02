package com.mxy.provide.version_2_0_0_asyn.facade.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxy.api.facade.GoodsFacade;
import com.mxy.api.facade.dto.GoodsDto;
import com.mxy.provide.version_2_0_0_asyn.domain.Goods;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service(version = "2.0.0")
public class GoodsFacadeImpl implements GoodsFacade {

    private Map<Long, List<Goods>> cacheMap = Maps.newHashMap();

    /**
     * 缓存模拟数据库
     */
    @PostConstruct
    public void cacheGoodsByOrderId() {
        cacheMap.put(1L, Lists.newArrayList(new Goods("洗发水", "白", 1)));
        cacheMap.put(2L, Lists.newArrayList(new Goods("衣服", "黑", 2)));
    }

    @Override
    public List<GoodsDto> getGoodsByOrderId(Long orderId) {
        if(CollectionUtils.isEmpty(cacheMap.get(orderId))){
            return Collections.emptyList();
        }
        return convert.apply(cacheMap.get(orderId));
    }

    private Function<List<Goods>, List<GoodsDto>> convert = goodsList ->
            goodsList.stream().map(c -> new GoodsDto(c.getGoodsName(), c.getGoodsColor(), c.getQutity())).collect(Collectors.toList());

}
