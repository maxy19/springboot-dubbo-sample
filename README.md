# Dubbo与SpringBoot集成

## dubbo 结构图：

![](http://dubbo.apache.org/docs/zh-cn/user/sources/images/dubbo-architecture.jpg)

## dubbo 整体结构图：

![](https://images2017.cnblogs.com/blog/1147548/201709/1147548-20170928141450169-1251868962.png)

Dubbo框架设计一共划分了10个层，最上面的Service层是留给实际想要使用Dubbo开发分布式服务的开发者实现业务逻辑的接口层。图中左边淡蓝背景的为服务消费方使用的接口，右边淡绿色背景的为服务提供方使用的接口， 位于中轴线上的为双方都用到的接口。
下面，结合Dubbo官方文档，我们分别理解一下框架分层架构中，各个层次的设计要点：
* 服务接口层（Service）：该层是与实际业务逻辑相关的，根据服务提供方和服务消费方的业务设计对应的接口和实现。
* 配置层（Config）：对外配置接口，以ServiceConfig和ReferenceConfig为中心，可以直接new配置类，也可以通过spring解析配置生成配置类。
* 服务代理层（Proxy）：服务接口透明代理，生成服务的客户端Stub和服务器端Skeleton，以ServiceProxy为中心，扩展接口为ProxyFactory。
* 服务注册层（Registry）：封装服务地址的注册与发现，以服务URL为中心，扩展接口为RegistryFactory、Registry和RegistryService。可能没有服务注册中心，此时服务提供方直接暴露服务。
* 集群层（Cluster）：封装多个提供者的路由及负载均衡，并桥接注册中心，以Invoker为中心，扩展接口为Cluster、Directory、Router和LoadBalance。将多个服务提供方组合为一个服务提供方，实现对服务消费方来透明，只需要与一个服务提供方进行交互。
* 监控层（Monitor）：RPC调用次数和调用时间监控，以Statistics为中心，扩展接口为MonitorFactory、Monitor和MonitorService。
远程调用层（Protocol）：封将RPC调用，以Invocation和Result为中心，扩展接口为Protocol、Invoker和Exporter。Protocol是服务域，它是Invoker暴露和引用的主功能入口，它负责Invoker的生命周期管理。Invoker是实体域，它是Dubbo的核心模型，其它模型都向它靠扰，或转换成它，它代表一个可执行体，可向它发起invoke调用，它有可能是一个本地的实现，也可能是一个远程的实现，也可能一个集群实现。
* 信息交换层（Exchange）：封装请求响应模式，同步转异步，以Request和Response为中心，扩展接口为Exchanger、ExchangeChannel、ExchangeClient和ExchangeServer。
* 网络传输层（Transport）：抽象mina和netty为统一接口，以Message为中心，扩展接口为Channel、Transporter、Client、Server和Codec。
* 数据序列化层（Serialize）：可复用的一些工具，扩展接口为Serialization、 ObjectInput、ObjectOutput和ThreadPool。



## 架构：
* Jdk 1.8
* Dubbo 2.6.2
* SpringBoot 2.1.4
* Zookeeper 3.4.9

  ## 本文通过不同例子来实现不同的dubbo功能(例子使用version区分)
  
  ### version：1.0.0 同步调用实现 已完成
      * 源码分析地址：整理中
  ### version：2.0.0 异步调用实现 已完成
      * 源码分析地址：整理中
  ### version：3.0.0 负载均衡实现 已完成
      * 源码分析地址：整理中
  ### version：4.0.0 本地缓存 已完成
      * 源码分析地址：整理中
  ### version：5.0.0 服务降级+本地伪装 已完成
      * 源码分析地址：整理中
  ### version：6.0.0 事件通知 已完成
      * 源码分析地址：整理中
  ### version：7.0.0 并发控制 已完成
      * 源码分析地址：整理中
  
  

        
       
