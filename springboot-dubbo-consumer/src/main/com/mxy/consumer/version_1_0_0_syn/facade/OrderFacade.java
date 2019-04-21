package com.mxy.consumer.version_1_0_0_syn.facade;

import com.mxy.consumer.version_1_0_0_syn.domain.Order;

import java.util.List;

public interface OrderFacade {

       List<Order> getOrderListByOrderId(Long id);


}
