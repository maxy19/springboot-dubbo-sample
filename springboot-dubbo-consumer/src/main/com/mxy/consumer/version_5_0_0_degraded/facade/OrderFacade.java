package com.mxy.consumer.version_5_0_0_degraded.facade;

import com.mxy.consumer.version_5_0_0_degraded.domain.Order;

import java.util.List;

public interface OrderFacade {

       List<Order> getOrderListByOrderId(Long id);


}
