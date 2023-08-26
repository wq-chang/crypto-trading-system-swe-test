package com.quan.cryptotradingsystem.service.impl;

import com.quan.cryptotradingsystem.model.OrderHistoryModel;
import com.quan.cryptotradingsystem.repository.OrderRepo;
import com.quan.cryptotradingsystem.repository.WalletRepo;
import com.quan.cryptotradingsystem.service.OrderQueryService;
import com.quan.cryptotradingsystem.service.UserQueryService;
import com.quan.cryptotradingsystem.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    private final UserQueryService userQueryService;
    private final WalletRepo walletRepo;
    private final OrderRepo orderRepo;

    @Autowired
    public OrderQueryServiceImpl(
            UserQueryService userQueryService, WalletRepo walletRepo, OrderRepo orderRepo) {
        this.userQueryService = userQueryService;
        this.walletRepo = walletRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public List<OrderHistoryModel> getOrderHistories() {
        var userId = userQueryService.getLoginUserId();
        var optWallet = walletRepo.findByUserId(userId);
        if (optWallet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "wallet not found");
        }
        var wallet = optWallet.get();
        var walletBalances = wallet.getBalances();
        var orders = orderRepo.findAllByFromWalletBalanceInOrToWalletBalanceIn(walletBalances, walletBalances);

        return orders.stream()
                .map(OrderMapper.INSTANCE::orderToOrderHistoryModel)
                .sorted((a, b) -> a.getTransactionDateTime().compareTo(b.getTransactionDateTime()))
                .collect(Collectors.toList());
    }
}
