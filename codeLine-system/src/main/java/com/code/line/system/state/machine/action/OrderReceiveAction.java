package com.code.line.system.state.machine.action;

import com.alibaba.fastjson.JSON;
import com.code.line.system.constant.OrderStatus;
import com.code.line.system.constant.OrderStatusChangeEvent;
import com.code.line.system.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @author: syl
 * @Date: 2022/6/26 00:18
 * @Description: 收货事件
 */
@Slf4j
@Component
public class OrderReceiveAction implements Action<OrderStatus, OrderStatusChangeEvent> {

    @Override
    public void execute(StateContext<OrderStatus, OrderStatusChangeEvent> context) {
        Order order = (Order)context.getMessageHeader("order");
        OrderStatusChangeEvent payload = context.getEvent();
        log.info("触发收货事件Action order={}", JSON.toJSONString(order));
        log.info("执行事件={}",payload.getDesc());
        log.info("---- ---- ---- ---- ---- ---- ---- ---- ");
    }
}
