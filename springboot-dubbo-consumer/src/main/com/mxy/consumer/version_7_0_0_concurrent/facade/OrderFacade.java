package com.mxy.consumer.version_7_0_0_concurrent.facade;

import com.mxy.consumer.version_7_0_0_concurrent.domain.Order;

import java.util.List;

public interface OrderFacade {

       List<Order> getOrderListByOrderId(Long id);


}
