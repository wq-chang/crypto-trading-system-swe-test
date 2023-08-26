package com.quan.cryptotradingsystem.service;

import com.quan.cryptotradingsystem.model.TradeOrderModel;

public interface TradeCommandService {

    public void exchangeCrypto(TradeOrderModel tradeOrderModel);
}
