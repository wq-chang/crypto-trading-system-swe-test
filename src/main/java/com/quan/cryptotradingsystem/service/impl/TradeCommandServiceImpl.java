package com.quan.cryptotradingsystem.service.impl;

import com.quan.cryptotradingsystem.entity.Order;
import com.quan.cryptotradingsystem.entity.WalletBalance;
import com.quan.cryptotradingsystem.enumeration.Action;
import com.quan.cryptotradingsystem.model.TradeOrderModel;
import com.quan.cryptotradingsystem.repository.CryptoPairRepo;
import com.quan.cryptotradingsystem.repository.OrderRepo;
import com.quan.cryptotradingsystem.repository.PriceRepo;
import com.quan.cryptotradingsystem.repository.WalletBalanceRepo;
import com.quan.cryptotradingsystem.repository.WalletRepo;
import com.quan.cryptotradingsystem.service.TradeCommandService;
import com.quan.cryptotradingsystem.service.UserQueryService;
import com.quan.cryptotradingsystem.utils.PriceUtils;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TradeCommandServiceImpl implements TradeCommandService {

    private final UserQueryService userQueryService;
    private final CryptoPairRepo cryptoPairRepo;
    private final OrderRepo orderRepo;
    private final PriceRepo priceRepo;
    private final WalletRepo walletRepo;
    private final WalletBalanceRepo walletBalanceRepo;

    @Autowired
    public TradeCommandServiceImpl(
            UserQueryService userQueryService,
            CryptoPairRepo cryptoPairRepo,
            OrderRepo orderRepo,
            PriceRepo priceRepo,
            WalletRepo walletRepo,
            WalletBalanceRepo walletBalanceRepo) {
        this.userQueryService = userQueryService;
        this.cryptoPairRepo = cryptoPairRepo;
        this.orderRepo = orderRepo;
        this.priceRepo = priceRepo;
        this.walletRepo = walletRepo;
        this.walletBalanceRepo = walletBalanceRepo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void exchangeCrypto(TradeOrderModel tradeOrderModel) {

        var optPrice = priceRepo.findBySymbol(tradeOrderModel.getSymbol());
        if (optPrice.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found");
        }
        var price = optPrice.get();

        var optCryptoPair = cryptoPairRepo.findBySymbol(tradeOrderModel.getSymbol());
        if (optCryptoPair.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Crypto pair not found");
        }
        var cryptoPair = optCryptoPair.get();

        var userId = userQueryService.getLoginUserId();
        var optWallet = walletRepo.findByUserId(userId);
        if (optWallet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found");
        }
        var wallet = optWallet.get();

        WalletBalance fromWalletBalance = null;
        WalletBalance toWalletBalance = null;
        var isBuyAction = Action.BUY.equals(tradeOrderModel.getAction());

        var walletBalances = walletBalanceRepo.findAllByWalletAndCryptoCodes(
                wallet, List.of(cryptoPair.getBaseCrypto(), cryptoPair.getPairCrypto()));
        for (var wb : walletBalances) {
            if (cryptoPair.getBaseCrypto().getCode().equals(wb.getSupportedCrypto().getCode())) {
                if (isBuyAction) {
                    fromWalletBalance = wb;
                } else {
                    toWalletBalance = wb;
                }
            } else if (cryptoPair.getPairCrypto().getCode().equals(wb.getSupportedCrypto().getCode())) {
                if (isBuyAction) {
                    toWalletBalance = wb;
                } else {
                    fromWalletBalance = wb;
                }
            }
        }

        if (fromWalletBalance == null) {
            fromWalletBalance = new WalletBalance(
                    wallet, isBuyAction ? cryptoPair.getBaseCrypto() : cryptoPair.getPairCrypto());
        }

        if (toWalletBalance == null) {
            toWalletBalance = new WalletBalance(
                    wallet, !isBuyAction ? cryptoPair.getBaseCrypto() : cryptoPair.getPairCrypto());
        }

        if (fromWalletBalance.getBalance().compareTo(tradeOrderModel.getAmount()) == -1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Issuficient balance");
        }
        var priceAmount = isBuyAction ? PriceUtils.convertPrice(price.getAsk()) : price.getBid();

        var toAmount = priceAmount.multiply(tradeOrderModel.getAmount());
        fromWalletBalance.setBalance(
                fromWalletBalance.getBalance().subtract(tradeOrderModel.getAmount()));
        toWalletBalance.setBalance(toWalletBalance.getBalance().add(toAmount));

        var order = new Order(
                tradeOrderModel.getSymbol(),
                fromWalletBalance,
                tradeOrderModel.getAmount(),
                fromWalletBalance.getBalance(),
                toWalletBalance,
                toAmount,
                toWalletBalance.getBalance(),
                isBuyAction ? price.getAsk() : price.getBid(),
                tradeOrderModel.getAction(),
                LocalDateTime.now());

        walletBalanceRepo.saveAll(List.of(fromWalletBalance, toWalletBalance));
        orderRepo.save(order);
    }
}
