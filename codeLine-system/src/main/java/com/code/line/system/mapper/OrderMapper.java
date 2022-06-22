package com.code.line.system.mapper;

import com.code.line.system.model.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: syl
 * @Date: 2022/6/22 00:13
 * @Description:
 */
@Component
public class OrderMapper {

    private static Map<Integer, Order> dateBase= new HashMap<>();
    private static int id=1;


    public Order save(Order order){
        if (order.getId() == null){
            order.setId(id++);
        }
        dateBase.put(order.getId(),order);
        return order;
    }

    public Order selectById(int id){
        return dateBase.get(id);
    }

}
