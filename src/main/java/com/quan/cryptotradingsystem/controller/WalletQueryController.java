package com.quan.cryptotradingsystem.controller;

import com.quan.cryptotradingsystem.model.WalletBalanceModel;
import com.quan.cryptotradingsystem.service.WalletQueryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletQueryController {

  private final WalletQueryService walletQueryService;

  @Autowired
  public WalletQueryController(WalletQueryService walletQueryService) {
    this.walletQueryService = walletQueryService;
  }

  @GetMapping("/wallet-balance")
  public ResponseEntity<List<WalletBalanceModel>> getLatestPrices() {
    return ResponseEntity.ok(walletQueryService.getWalletBalances());
  }
}
