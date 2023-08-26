package com.quan.cryptotradingsystem.controller;

import com.quan.cryptotradingsystem.model.TradeOrderModel;
import com.quan.cryptotradingsystem.model.WalletBalanceModel;
import com.quan.cryptotradingsystem.service.TradeCommandService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TradeCommandController {

    private final TradeCommandService tradeCommandService;

    @Autowired
    public TradeCommandController(TradeCommandService tradeCommandService) {
        this.tradeCommandService = tradeCommandService;
    }

    @PostMapping("/trade")
    public ResponseEntity<List<WalletBalanceModel>> getLatestPrices(
            @Valid @RequestBody TradeOrderModel tradeOrderModel) {
        tradeCommandService.exchangeCrypto(tradeOrderModel);
        return ResponseEntity.ok().build();
    }
}
