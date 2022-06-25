package com.code.line.system.constant;

import lombok.Getter;

/**
 * @author: syl
 * @Date: 2022/6/22 00:11
 * @Description: 订单事件
 */
@Getter
public enum OrderStatusChangeEvent {
    PAYED("支付"),
    DELIVERY("发货"),
    RECEIVED("收获");

    //状态描述
    private String desc;

    OrderStatusChangeEvent(String desc){
        this.desc=desc;
    }

}
