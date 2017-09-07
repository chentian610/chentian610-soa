package com.chentian610.capital.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chentian610.capital.service.CapitalService;
import com.chentian610.capital.vo.CapitalAccount;
import com.chentian610.capital.vo.CapitalTradeOrderDto;
import com.chentian610.capital.vo.TradeOrder;
import com.chentian610.framework.BaseController;
import com.chentian610.framework.GeneralDAO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;

@Service
public class CapitalServiceImpl implements CapitalService {

	private static Logger logger = LoggerFactory.getLogger(CapitalServiceImpl.class);

	@Autowired
	private GeneralDAO dao;

	@Override
	public BigDecimal getCapitalAccountByUserId(long userId) {
		CapitalAccount capitalAccount = dao.queryObject("capitalMap.findByUserId",userId);
		return capitalAccount.getBalanceAmount();
	}

	@Override
	@Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = DubboTransactionContextEditor.class)
	public String record(CapitalTradeOrderDto tradeOrderDto) {

//		try {
//			Thread.sleep(1000l);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}

		logger.info("capital try record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

		TradeOrder tradeOrder = new TradeOrder(
				tradeOrderDto.getSelfUserId(),
				tradeOrderDto.getOppositeUserId(),
				tradeOrderDto.getMerchantOrderNo(),
				tradeOrderDto.getAmount()
		);

		dao.insertObject("tradeOrderMap.insert",tradeOrder);

		CapitalAccount transferFromAccount = dao.queryObject("capitalMap.findByUserId",tradeOrderDto.getSelfUserId());

		transferFromAccount.transferFrom(tradeOrderDto.getAmount());

		dao.updateObject("capitalMap.update",transferFromAccount);
		return "success";
	}

	@Transactional
	public void confirmRecord(CapitalTradeOrderDto tradeOrderDto) {
//		try {
//			Thread.sleep(1000l);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
		logger.info("capital confirm record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

		TradeOrder tradeOrder = dao.queryObject("tradeOrderMap.findByMerchantOrderNo",tradeOrderDto.getMerchantOrderNo());

		tradeOrder.confirm();
		dao.updateObject("tradeOrderMap.update",tradeOrder);

		CapitalAccount transferToAccount = dao.queryObject("capitalMap.findByUserId",tradeOrderDto.getOppositeUserId());

		transferToAccount.transferTo(tradeOrderDto.getAmount());

		dao.updateObject("capitalMap.update",transferToAccount);
	}

	@Transactional
	public void cancelRecord(CapitalTradeOrderDto tradeOrderDto) {
//		try {
//			Thread.sleep(1000l);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}

		logger.info("capital cancel record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

		TradeOrder tradeOrder = dao.queryObject("tradeOrderMap.findByMerchantOrderNo",tradeOrderDto.getMerchantOrderNo());

		if (null != tradeOrder && "DRAFT".equals(tradeOrder.getStatus())) {
			tradeOrder.cancel();
			dao.updateObject("tradeOrderMap.update",tradeOrder);


			CapitalAccount transferToAccount = dao.queryObject("capitalMap.findByUserId",tradeOrderDto.getOppositeUserId());

			transferToAccount.transferTo(tradeOrderDto.getAmount());

			dao.updateObject("capitalMap.update",transferToAccount);
		}
	}

}
