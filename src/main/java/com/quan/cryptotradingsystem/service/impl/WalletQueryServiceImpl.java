package com.quan.cryptotradingsystem.service.impl;

import com.quan.cryptotradingsystem.model.WalletBalanceModel;
import com.quan.cryptotradingsystem.repository.WalletRepo;
import com.quan.cryptotradingsystem.service.UserQueryService;
import com.quan.cryptotradingsystem.service.WalletQueryService;
import com.quan.cryptotradingsystem.service.mapper.WalletMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WalletQueryServiceImpl implements WalletQueryService {

    private final UserQueryService userQueryService;
    private final WalletRepo walletRepo;

    @Autowired
    public WalletQueryServiceImpl(UserQueryService userQueryService, WalletRepo walletRepo) {
        this.userQueryService = userQueryService;
        this.walletRepo = walletRepo;
    }

    @Override
    public List<WalletBalanceModel> getWalletBalances() {
        var loginUserId = userQueryService.getLoginUserId();
        var optWallet = walletRepo.findByUserId(loginUserId);

        if (optWallet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found");
        }

        return optWallet.get().getBalances().stream()
                .map(WalletMapper.INSTANCE::walletBalanceToWalletBalanceModel)
                .collect(Collectors.toList());
    }
}
