package com.code.line.system.model;

import com.code.line.system.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: syl
 * @Date: 2022/6/22 00:12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /** 订单id */
    private Integer id;
    /** 状态 */
    private OrderStatus status;
}
