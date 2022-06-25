package com.code.line.system.config;

import com.code.line.system.constant.OrderStatus;
import com.code.line.system.constant.OrderStatusChangeEvent;
import com.code.line.system.model.Order;
import com.code.line.system.state.machine.OrderStateMachineDao;
import com.code.line.system.state.machine.action.OrderDeliverAction;
import com.code.line.system.state.machine.action.OrderPayAction;
import com.code.line.system.state.machine.action.OrderReceiveAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 * @author: syl
 * @Date: 2022/6/22 00:15
 * @Description:
 */
@Slf4j
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {

    @Resource
    private OrderStateMachineDao orderStateMachineDao;
    /**
     * 初始化状态机
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatus.WAIT_PAYMAENT)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    @Autowired
    private OrderPayAction payAction;
    @Autowired
    private OrderDeliverAction deliverAction;
    @Autowired
    private OrderReceiveAction orderReceiveAction;

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions)
            throws Exception {
        transitions
                .withExternal().source(OrderStatus.WAIT_PAYMAENT).target(OrderStatus.WAIT_DELIVER).event(OrderStatusChangeEvent.PAYED).action(payAction).and()
                .withExternal().source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE).event(OrderStatusChangeEvent.DELIVERY).action(deliverAction).and()
                .withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderStatusChangeEvent.RECEIVED).action(orderReceiveAction);
    }

    @Bean
    public StateMachinePersister<OrderStatus,OrderStatusChangeEvent, Order> orderStateMachinePersister(){
        return new DefaultStateMachinePersister<>(orderStateMachineDao);
    }



}
