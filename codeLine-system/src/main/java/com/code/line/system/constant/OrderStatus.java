package com.code.line.system.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: syl
 * @Date: 2022/6/22 00:10
 * @Description: 订单状态
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
    WAIT_PAYMAENT("等待支付"),
    WAIT_DELIVER("待发货"),
    WAIT_RECEIVE("待收货"),
    FINISH("完成");

    //状态描述
    public String desc;


}
