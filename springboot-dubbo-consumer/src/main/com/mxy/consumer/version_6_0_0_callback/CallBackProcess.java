package com.mxy.consumer.version_6_0_0_callback;

import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.google.common.collect.Lists;
import com.mxy.api.facade.callback.CallBackServcie;
import com.mxy.consumer.version_6_0_0_callback.service.Impl.NotifyServiceImpl;
import com.mxy.consumer.version_6_0_0_callback.service.Notify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
@Controller
@RequestMapping("/6_0_0/process")
@Slf4j
public class CallBackProcess {

    private CallBackServcie callbackService;

    /**
     * 1.在调用之前、调用之后、出现异常时，会触发 oninvoke、onreturn、onthrow 三个事件，可以配置当事件发生时，通知哪个类的哪个方法
     * 2.callback 与 async 功能正交分解，async=true 表示结果是否马上返回，onreturn 表示是否需要回调。
     *   两者叠加存在以下几种组合情况 [2]：
     *   异步回调模式：async=true onreturn="xxx"
     *   同步回调模式：async=false onreturn="xxx"
     *   异步无回调 ：async=true
     *   同步无回调 ：async=false
     * 3.
     *  oninvoke方法：
     *          必须具有与真实的被调用方法sayHello相同的入参列表：例如，oninvoke(String name)
     *  onreturn方法：
     *          至少要有一个入参且第一个入参必须与sayHello的返回类型相同，接收返回结果：例如，onreturnWithoutParam(String result)
     *          可以有多个参数，多个参数的情况下，第一个后边的所有参数都是用来接收sayHello入参的：例如， onreturn(String result, String name)
     *  onthrow方法：
     *        至少要有一个入参且第一个入参类型为Throwable或其子类，接收返回结果；例如，onthrow(Throwable ex)
     *        可以有多个参数，多个参数的情况下，第一个后边的所有参数都是用来接收sayHello入参的：例如，onthrow(Throwable ex, String name)
     *        如果是consumer在调用provider的过程中，出现异常时不会走onthrow方法的，onthrow方法只会在provider返回的RpcResult中含有Exception对象时，才会执行。
     *        （dubbo中下层服务的Exception会被放在响应RpcResult的exception对象中传递给上层服务）
     */
    @PostConstruct
    public void before(){
        Notify notify = new NotifyServiceImpl();
        ReferenceConfig<CallBackServcie> reference = new ReferenceConfig();
        reference.setTimeout(10000);
        reference.setInterface(CallBackServcie.class);
        reference.setVersion("6.0.0");
        reference.setCheck(false);
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("addListener");
        methodConfig.setOnreturn(notify);
        methodConfig.setOnreturnMethod("onReturn");
        methodConfig.setOnthrow(notify);
        methodConfig.setOnthrowMethod("testThrow");
        reference.setMethods(Lists.newArrayList(methodConfig));
        callbackService = reference.get();
    }

    @RequestMapping
    public void process() {

        //测试onreturn
        callbackService.addListener("test");
        //测试onThrow
        callbackService.addListener(null);
    }

}
