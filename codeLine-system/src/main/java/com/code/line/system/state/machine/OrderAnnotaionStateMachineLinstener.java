package com.code.line.system.state.machine;

import com.alibaba.fastjson.JSON;
import com.code.line.system.constant.OrderStatus;
import com.code.line.system.constant.OrderStatusChangeEvent;
import com.code.line.system.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * @author: syl
 * @Date: 2022/6/23 00:10
 * @Description: 注解式监听器
 */
@Slf4j
@Component("OrderAnnotaionStateMachineLinstener")
@WithStateMachine(name = "orderStateMachine")
public class OrderAnnotaionStateMachineLinstener {

    @OnTransition(source = "WAIT_PAYMAENT",target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderStatusChangeEvent> message){
        Order order = (Order)message.getHeaders().get("order");
        OrderStatusChangeEvent payload = message.getPayload();
        log.info("监听到触发支付请求 order={}", JSON.toJSONString(order));
        log.info("---- ---- ---- ---- ---- ---- ---- ---- ");
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER",target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderStatusChangeEvent> message){
        Order order = (Order)message.getHeaders().get("order");
        OrderStatusChangeEvent payload = message.getPayload();
        log.info("监听到触发发货请求 order={}", JSON.toJSONString(order));
        log.info("---- ---- ---- ---- ---- ---- ---- ---- ");
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE",target = "FINISH")
    public boolean receiveTransition(Message<OrderStatusChangeEvent> message){
        Order order = (Order)message.getHeaders().get("order");
        OrderStatusChangeEvent payload = message.getPayload();
        log.info("监听到触发收获请求 order={}", JSON.toJSONString(order));
        log.info("---- ---- ---- ---- ---- ---- ---- ---- ");
        return true;
    }

}
