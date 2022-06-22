package com.code.line.system.service.impl;

import com.code.line.system.constant.OrderStatus;
import com.code.line.system.constant.OrderStatusChangeEvent;
import com.code.line.system.mapper.OrderMapper;
import com.code.line.system.model.Order;
import com.code.line.system.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author: syl
 * @Date: 2022/6/22 22:32
 * @Description:
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;
    @Resource
    private StateMachinePersister<OrderStatus,OrderStatusChangeEvent,Order> persister;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order create() {
        Order order = new Order();
        order.setStatus(OrderStatus.WAIT_PAYMAENT);
        return orderMapper.save(order);
    }

    @Override
    public Order pay(int id) {
        Order order = orderMapper.selectById(id);
        if (!sendEvent(OrderStatusChangeEvent.PAYED,order)){
            throw new RuntimeException(" 等待支付 -> 等待发货 失败, 状态异常 order=" + order);
        }
        return order;
    }

    @Override
    public Order deliver(int id) {
        Order order = orderMapper.selectById(id);
        if (!sendEvent(OrderStatusChangeEvent.DELIVERY,order)){
            throw new RuntimeException(" 等待支付 -> 等待发货 失败, 状态异常 order=" + order);
        }
        return order;
    }

    @Override
    public Order receive(int id) {
        Order order = orderMapper.selectById(id);
        if (!sendEvent(OrderStatusChangeEvent.RECEIVED,order)){
            throw new RuntimeException(" 等待收货 -> 完成 失败，状态异常 order=" + order);
        }
        return order;
    }

    /**
     * 发送订单状态转换事件
     *
     * @param event 事件
     * @param order 订单
     * @return 执行结果
     */
    private boolean sendEvent(OrderStatusChangeEvent event, Order order) {
        boolean result = false;
        try {
            orderStateMachine.startReactively().block();
            // 设置状态机状态
            persister.restore(orderStateMachine, order);
            result = orderStateMachine.sendEvent(MessageBuilder.withPayload(event).setHeader("order", order).build());
            // 保存状态机状态
            persister.persist(orderStateMachine, order);
        } catch (Exception e) {
            log.error("状态机发布事件异常，e={}",e);
        } finally {
            orderStateMachine.stopReactively().block();
        }
        return result;
    }
}
