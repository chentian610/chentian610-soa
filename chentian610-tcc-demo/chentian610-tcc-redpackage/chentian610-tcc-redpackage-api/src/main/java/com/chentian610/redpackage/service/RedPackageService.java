package com.chentian610.redpackage.service;

import com.chentian610.redpackage.vo.RedPacketTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;

import java.math.BigDecimal;

public interface RedPackageService {
    BigDecimal getRedPacketAccountByUserId(long userId);

    @Compensable
    public String record(RedPacketTradeOrderDto tradeOrderDto);
}
