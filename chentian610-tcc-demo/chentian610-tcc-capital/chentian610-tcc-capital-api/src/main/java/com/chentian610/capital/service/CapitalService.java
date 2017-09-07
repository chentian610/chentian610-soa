package com.chentian610.capital.service;

import com.chentian610.capital.vo.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface CapitalService {
    BigDecimal getCapitalAccountByUserId(long userId);

    @Compensable
    public String record(CapitalTradeOrderDto tradeOrderDto);
}
