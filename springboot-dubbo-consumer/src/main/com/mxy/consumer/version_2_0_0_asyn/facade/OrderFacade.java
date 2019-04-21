package com.mxy.consumer.version_2_0_0_asyn.facade;

import com.mxy.consumer.version_2_0_0_asyn.domain.Order;

import java.util.List;

public interface OrderFacade {

       List<Order> getOrderListByOrderId(Long id);


}
