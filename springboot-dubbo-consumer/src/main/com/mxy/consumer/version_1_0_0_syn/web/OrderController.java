package com.mxy.consumer.version_1_0_0_syn.web;

import com.alibaba.fastjson.JSON;
import com.mxy.consumer.version_1_0_0_syn.facade.OrderFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderFacade orderFacade;

    @RequestMapping("/1_0_0/getOrderById")
    public @ResponseBody String getOrder(Long id){
        return JSON.toJSON(orderFacade.getOrderListByOrderId(id).toString()).toString();
    }

}
