package com.quan.cryptotradingsystem.service.impl;

import com.quan.cryptotradingsystem.model.PriceModel;
import com.quan.cryptotradingsystem.service.PriceQueryService;
import com.quan.cryptotradingsystem.service.mapper.PriceMapper;
import com.quan.cryptotradingsystem.service.repository.PriceRepo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PriceQueryServiceImpl implements PriceQueryService {

  private final PriceRepo priceRepo;

  public PriceQueryServiceImpl(PriceRepo priceRepo) {
    this.priceRepo = priceRepo;
  }

  @Override
  @Transactional(readOnly = true)
  public List<PriceModel> getBestPrices() {
    var prices = priceRepo.findAll();
    return prices.stream()
        .map(PriceMapper.INSTANCE::priceToPriceModel)
        .collect(Collectors.toList());
  }
}
