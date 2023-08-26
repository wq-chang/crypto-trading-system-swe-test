package com.quan.cryptotradingsystem.controller;

import com.quan.cryptotradingsystem.model.PriceModel;
import com.quan.cryptotradingsystem.service.PriceQueryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceQueryController {

  private final PriceQueryService priceQueryService;

  @Autowired
  public PriceQueryController(PriceQueryService priceQueryService) {
    this.priceQueryService = priceQueryService;
  }

  @GetMapping("/latest-prices")
  public ResponseEntity<List<PriceModel>> getLatestPrices() {
    return ResponseEntity.ok(priceQueryService.getBestPrices());
  }
}
