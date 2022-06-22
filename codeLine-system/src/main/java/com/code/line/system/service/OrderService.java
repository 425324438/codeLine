package com.code.line.system.service;

import com.code.line.system.model.Order;

/**
 * @author: syl
 * @Date: 2022/6/22 22:31
 * @Description:
 */
public interface OrderService {
    /**
     * 创建订单
     * @return Order
     */
    Order create();

    /**
     * 支付
     * @param  id
     * @return
     */
    Order pay(int id);

    /**
     * 发货
     * @param id
     * @return
     */
    Order deliver(int id);

    /**
     * 接收
     * @param id
     * @return
     */
    Order receive(int id);
}
