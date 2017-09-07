package com.chentian610.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chentian610.capital.service.CapitalService;
import com.chentian610.order.service.impl.OrderServiceImpl;
import com.chentian610.order.service.impl.PlaceOrderServiceImpl;
import com.chentian610.order.vo.PlaceOrderRequest;
import com.chentian610.order.vo.Product;
import com.chentian610.redpackage.service.RedPackageService;
import com.chentian610.framework.GeneralDAO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by changming.xie on 4/1/16.
 */
@Controller
@RequestMapping("")
public class OrderController {

    @Autowired
    GeneralDAO dao;

    @Reference
    CapitalService capitalService;

    @Reference
    private RedPackageService redpackageService;

    @Autowired
    PlaceOrderServiceImpl placeOrderService;



    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/index");
        return mv;
    }

    @RequestMapping(value = "/user/{userId}/shop/{shopId}",method = RequestMethod.GET)
    public ModelAndView getProductsInShop(@PathVariable long userId,
                                          @PathVariable long shopId){
        List<Product> products = dao.queryForList("productMap.findByShopId",shopId);

        ModelAndView mv = new ModelAndView("/shop");

        mv.addObject("products",products);
        mv.addObject("userId",userId);
        mv.addObject("shopId",shopId);

        return mv;
    }

    @RequestMapping(value = "/user/{userId}/shop/{shopId}/product/{productId}/confirm",method = RequestMethod.GET)
    public ModelAndView productDetail(@PathVariable long userId,
                                      @PathVariable long shopId,
                                      @PathVariable long productId){

        ModelAndView mv = new ModelAndView("product_detail");

        mv.addObject("capitalAmount",capitalService.getCapitalAccountByUserId(userId));
        mv.addObject("redPacketAmount",redpackageService.getRedPacketAccountByUserId(userId));

        mv.addObject("product",dao.queryObject("productMap.findById",productId));

        mv.addObject("userId",userId);
        mv.addObject("shopId",shopId);

        return mv;
    }

    @RequestMapping(value = "/placeorder", method = RequestMethod.POST)
    public ModelAndView placeOrder(@RequestParam String redPacketPayAmount,
                                   @RequestParam long shopId,
                                   @RequestParam long payerUserId,
                                   @RequestParam long productId) {


        PlaceOrderRequest request = buildRequest(redPacketPayAmount,shopId,payerUserId,productId);

        String merchantOrderNo = placeOrderService.placeOrder(request.getPayerUserId(), request.getShopId(),
                request.getProductQuantities(), request.getRedPacketPayAmount());

        ModelAndView mv = new ModelAndView("pay_success");

        String payResultTip = null;
        String status = orderService.getOrderStatusByMerchantOrderNo(merchantOrderNo);
        System.out.println("order "+status+"time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        if("CONFIRMED".equals(status))
            payResultTip = "支付成功";
        else if("PAY_FAILED".equals(status))
            payResultTip = "支付失败";
        else if ("PAYING".equals(status))
            payResultTip = "支付中";

        mv.addObject("payResult",payResultTip);
        mv.addObject("product",dao.queryObject("productMap.findById",productId));

        mv.addObject("capitalAmount",capitalService.getCapitalAccountByUserId(payerUserId));
        mv.addObject("redPacketAmount",redpackageService.getRedPacketAccountByUserId(payerUserId));

        return mv;
    }


    private PlaceOrderRequest buildRequest(String redPacketPayAmount,long shopId,long payerUserId,long productId) {
        BigDecimal redPacketPayAmountInBigDecimal = new BigDecimal(redPacketPayAmount);
        if(redPacketPayAmountInBigDecimal.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidParameterException("invalid red packet amount :" + redPacketPayAmount);

        PlaceOrderRequest request = new PlaceOrderRequest();
        request.setPayerUserId(payerUserId);
        request.setShopId(shopId);
        request.setRedPacketPayAmount(new BigDecimal(redPacketPayAmount));
        request.getProductQuantities().add(new ImmutablePair<Long, Integer>(productId, 1));
        return request;
    }
}
