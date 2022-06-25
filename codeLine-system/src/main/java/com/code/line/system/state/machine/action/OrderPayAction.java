package com.code.line.system.state.machine.action;

import com.alibaba.fastjson.JSON;
import com.code.line.system.constant.OrderStatus;
import com.code.line.system.constant.OrderStatusChangeEvent;
import com.code.line.system.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: syl
 * @Date: 2022/6/26 00:09
 * @Description: 订单支付事件
 */
@Slf4j
@Component
public class OrderPayAction implements Action<OrderStatus, OrderStatusChangeEvent> {


    /**
     * 订单支付逻辑
     * @param context
     */
    @Override
    public void execute(StateContext<OrderStatus, OrderStatusChangeEvent> context) {
        Order order = (Order)context.getMessageHeader("order");
        OrderStatusChangeEvent payload = context.getEvent();
        log.info("触发支付事件Action order={}", JSON.toJSONString(order));
        log.info("执行事件={}",payload.getDesc());
        log.info("---- ---- ---- ---- ---- ---- ---- ---- ");
    }
}
