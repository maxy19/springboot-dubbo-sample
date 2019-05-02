package com.mxy.consumer.version_4_0_0_cache.web;

import com.alibaba.fastjson.JSON;
import com.mxy.consumer.version_2_0_0_asyn.facade.OrderFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderFacade orderFacade;

    @RequestMapping("/4_0_0/getOrderById")
    public @ResponseBody String getOrder(Long id){
        return JSON.toJSON(orderFacade.getOrderListByOrderId(id).toString()).toString();
    }

}
