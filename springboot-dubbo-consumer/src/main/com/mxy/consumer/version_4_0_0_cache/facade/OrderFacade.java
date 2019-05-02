package com.mxy.consumer.version_4_0_0_cache.facade;

import com.mxy.consumer.version_4_0_0_cache.domain.Order;

import java.util.List;

public interface OrderFacade {

       List<Order> getOrderListByOrderId(Long id);


}
