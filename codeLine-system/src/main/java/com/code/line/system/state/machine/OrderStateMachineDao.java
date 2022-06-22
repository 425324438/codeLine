package com.code.line.system.state.machine;

import com.alibaba.fastjson.JSON;
import com.code.line.system.config.OrderStateMachineConfig;
import com.code.line.system.constant.OrderStatus;
import com.code.line.system.constant.OrderStatusChangeEvent;
import com.code.line.system.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

/**
 * @author: syl
 * @Date: 2022/6/22 22:04
 * @Description: DefaultStateMachinePersister order 持久化
 */
@Slf4j
@Service
public class OrderStateMachineDao implements StateMachinePersist<OrderStatus, OrderStatusChangeEvent, Order> {


    @Override
    public void write(StateMachineContext<OrderStatus, OrderStatusChangeEvent> context, Order order)
            throws Exception {
        if (order.getStatus().equals(context.getState())){
            log.info("状态没有发生变化，不需要写入数据库 order={}",JSON.toJSONString(order));
        } else {
            log.info("持久化操作，写入order状态到数据库");
            order.setStatus(context.getState());
        }
    }

    @Override
    public StateMachineContext<OrderStatus, OrderStatusChangeEvent> read(Order order) throws Exception {
        log.info("读取状态并设置到context中，order={}", JSON.toJSONString(order));
        return new DefaultStateMachineContext<>(order.getStatus(), null, null, null);
    }
}
