//package com.code.line.system.controller;
//
//import com.code.line.system.entity.SysConfig;
//import com.code.line.system.model.Order;
//import com.code.line.system.service.ISysConfigService;
//import com.code.line.system.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author: syl
// * @Date: 2022/6/21 23:38
// * @Description:
// */
//@RestController
//@RequestMapping("/test")
//public class TestController {
//
//    @Autowired
//    private OrderService orderService;
//
//    /**
//     * 创建订单
//     * @return Order
//     */
//    @RequestMapping("/create")
//    public Order create(){
//        return orderService.create();
//    }
//
//    /**
//     * 支付
//     * @param  id
//     * @return
//     */
//    @RequestMapping("/pay")
//    public Order pay(int id){
//        return orderService.pay(id);
//    }
//
//    /**
//     * 发货
//     * @param id
//     * @return
//     */
//    @RequestMapping("/deliver")
//    public Order deliver(int id){
//        return orderService.deliver(id);
//    }
//
//    /**
//     * 接收
//     * @param id
//     * @return
//     */
//    @RequestMapping("/receive")
//    public Order receive(int id){
//        return orderService.receive(id);
//    }
//
//
//}
