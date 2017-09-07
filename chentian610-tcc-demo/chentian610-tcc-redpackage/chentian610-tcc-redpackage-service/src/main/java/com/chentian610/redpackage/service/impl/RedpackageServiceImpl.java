package com.chentian610.redpackage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chentian610.redpackage.service.RedPackageService;
import com.chentian610.redpackage.vo.RedPacketAccount;
import com.chentian610.redpackage.vo.RedPacketTradeOrderDto;
import com.chentian610.redpackage.vo.TradeOrder;
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
public class RedpackageServiceImpl implements RedPackageService {

	private static Logger logger = LoggerFactory.getLogger(RedpackageServiceImpl.class);

	@Autowired
	private GeneralDAO dao;

	@Override
	public BigDecimal getRedPacketAccountByUserId(long userId) {
		RedPacketAccount redPacketAccount = dao.queryObject("redpacketaccount.findByUserId",userId);
		return redPacketAccount.getBalanceAmount();
	}

	@Override
	@Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = DubboTransactionContextEditor.class)
	@Transactional
	public String record(RedPacketTradeOrderDto tradeOrderDto) {

//		try {
//			Thread.sleep(1000l);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}

		logger.info("red packet try record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

		TradeOrder tradeOrder = new TradeOrder(
				tradeOrderDto.getSelfUserId(),
				tradeOrderDto.getOppositeUserId(),
				tradeOrderDto.getMerchantOrderNo(),
				tradeOrderDto.getAmount()
		);

		dao.insertObject("tradeorder.insert",tradeOrder);

		RedPacketAccount transferFromAccount = dao.queryObject("redpacketaccount.findByUserId",tradeOrderDto.getSelfUserId());

		transferFromAccount.transferFrom(tradeOrderDto.getAmount());

		dao.updateObject("redpacketaccount.update",transferFromAccount);

		return "success";
	}

	@Transactional
	public void confirmRecord(RedPacketTradeOrderDto tradeOrderDto) {

//		try {
//			Thread.sleep(1000l);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}

		logger.info("red packet confirm record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

		TradeOrder tradeOrder = dao.queryObject("tradeorder.findByMerchantOrderNo",tradeOrderDto.getMerchantOrderNo());

		tradeOrder.confirm();
		dao.updateObject("tradeorder.update",tradeOrder);

		RedPacketAccount transferToAccount = dao.queryObject("redpacketaccount.findByUserId",tradeOrderDto.getOppositeUserId());

		transferToAccount.transferTo(tradeOrderDto.getAmount());

		dao.updateObject("redpacketaccount.update",transferToAccount);
	}

	@Transactional
	public void cancelRecord(RedPacketTradeOrderDto tradeOrderDto) {
//
//		try {
//			Thread.sleep(1000l);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}

		logger.info("red packet cancel record called. time seq:" + DateFormatUtils.format(Calendar.getInstance(),"yyyy-MM-dd HH:mm:ss"));

		TradeOrder tradeOrder = dao.queryObject("tradeorder.findByMerchantOrderNo",tradeOrderDto.getMerchantOrderNo());

		if (null != tradeOrder && "DRAFT".equals(tradeOrder.getStatus())) {
			tradeOrder.cancel();
			dao.updateObject("tradeorder.update",tradeOrder);

			RedPacketAccount capitalAccount = dao.queryObject("redpacketaccount.findByUserId",tradeOrderDto.getSelfUserId());

			capitalAccount.cancelTransfer(tradeOrderDto.getAmount());

			dao.updateObject("redpacketaccount.update",capitalAccount);
		}
	}

}
