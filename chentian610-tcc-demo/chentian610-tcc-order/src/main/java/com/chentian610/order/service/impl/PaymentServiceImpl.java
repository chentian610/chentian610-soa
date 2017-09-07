package com.chentian610.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chentian610.capital.service.CapitalService;
import com.chentian610.capital.vo.CapitalTradeOrderDto;
import com.chentian610.framework.GeneralDAO;
import com.chentian610.order.vo.Order;
import com.chentian610.redpackage.service.RedPackageService;
import com.chentian610.redpackage.vo.RedPacketTradeOrderDto;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.mengyun.tcctransaction.api.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by changming.xie on 4/1/16.
 */
@Service
public class PaymentServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Reference(retries = 0,timeout = 5000)
    CapitalService capitalTradeOrderService;

    @Reference(retries = 0,timeout = 5000)
    RedPackageService redPacketTradeOrderService;

    @Autowired
    GeneralDAO dao;

    @Compensable(confirmMethod = "confirmMakePayment", cancelMethod = "cancelMakePayment")
    @Transactional
    public void makePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {
        logger.info("order try make payment called.time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        order.pay(redPacketPayAmount, capitalPayAmount);
        dao.updateObject("orderMap.update",order);

        String result = capitalTradeOrderService.record(buildCapitalTradeOrderDto(order));
        String result2 = redPacketTradeOrderService.record(buildRedPacketTradeOrderDto(order));
        logger.info("result="+result+",result2="+result2);
    }

    @Transactional
    public void confirmMakePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {


        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("order confirm make payment called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        order.confirm();

        dao.updateObject("orderMap.update",order);
        throw new RuntimeException("dddddd");
    }

    @Transactional
    public void cancelMakePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("order cancel make payment called.time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        order.cancelPayment();

        dao.updateObject("orderMap.updateOrder",order);
    }


    private CapitalTradeOrderDto buildCapitalTradeOrderDto(Order order) {

        CapitalTradeOrderDto tradeOrderDto = new CapitalTradeOrderDto();
        tradeOrderDto.setAmount(order.getCapitalPayAmount());
        tradeOrderDto.setMerchantOrderNo(order.getMerchantOrderNo());
        tradeOrderDto.setSelfUserId(order.getPayerUserId());
        tradeOrderDto.setOppositeUserId(order.getPayeeUserId());
        tradeOrderDto.setOrderTitle(String.format("order no:%s", order.getMerchantOrderNo()));

        return tradeOrderDto;
    }

    private RedPacketTradeOrderDto buildRedPacketTradeOrderDto(Order order) {
        RedPacketTradeOrderDto tradeOrderDto = new RedPacketTradeOrderDto();
        tradeOrderDto.setAmount(order.getRedPacketPayAmount());
        tradeOrderDto.setMerchantOrderNo(order.getMerchantOrderNo());
        tradeOrderDto.setSelfUserId(order.getPayerUserId());
        tradeOrderDto.setOppositeUserId(order.getPayeeUserId());
        tradeOrderDto.setOrderTitle(String.format("order no:%s", order.getMerchantOrderNo()));

        return tradeOrderDto;
    }
}
