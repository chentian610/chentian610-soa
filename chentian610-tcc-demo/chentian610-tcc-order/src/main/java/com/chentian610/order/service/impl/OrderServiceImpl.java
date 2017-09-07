package com.chentian610.order.service.impl;

import com.chentian610.order.OrderFactory;
import com.chentian610.order.vo.Order;
import com.chentian610.order.vo.OrderLine;
import com.chentian610.framework.GeneralDAO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by changming.xie on 3/25/16.
 */
@Service
public class OrderServiceImpl {

    @Autowired
    GeneralDAO dao;

    @Autowired
    OrderFactory orderFactory;

    @Transactional
    public Order createOrder(long payerUserId, long payeeUserId, List<Pair<Long, Integer>> productQuantities) {
        Order order = orderFactory.buildOrder(payerUserId, payeeUserId, productQuantities);

        dao.insertObject("orderMap.insert",order);

        for(OrderLine orderLine:order.getOrderLines()) {
            dao.insertObject("orderLineMap.insert",orderLine);
        }

        return order;
    }

    public String getOrderStatusByMerchantOrderNo(String orderNo){
        Order order = dao.queryObject("orderMap.findByMerchantOrderNo",orderNo);
        return order.getStatus();
    }
}
