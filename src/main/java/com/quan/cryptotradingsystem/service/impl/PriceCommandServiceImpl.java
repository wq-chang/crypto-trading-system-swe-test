package com.quan.cryptotradingsystem.service.impl;

import com.quan.cryptotradingsystem.adapter.PriceAdapter;
import com.quan.cryptotradingsystem.entity.Price;
import com.quan.cryptotradingsystem.model.PriceModel;
import com.quan.cryptotradingsystem.repository.PriceRepo;
import com.quan.cryptotradingsystem.service.PriceCommandService;
import com.quan.cryptotradingsystem.service.mapper.PriceMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PriceCommandServiceImpl implements PriceCommandService {

  @Value("${app.api.timeout}")
  private long timeout;

  private final PriceAdapter bianceAdapter;
  private final PriceAdapter huoBiAdapter;
  private final PriceRepo priceRepo;

  @Autowired
  public PriceCommandServiceImpl(
      @Qualifier("biance") PriceAdapter bianceAdapter,
      @Qualifier("huoBi") PriceAdapter huobiAdapter,
      PriceRepo priceRepo) {
    this.bianceAdapter = bianceAdapter;
    this.huoBiAdapter = huobiAdapter;
    this.priceRepo = priceRepo;
  }

  @Override
  public void fetchAndSaveLatestPrices() {
    var bianceFuture =
        CompletableFuture.supplyAsync(
            () -> this.fetchLatestPricesByApi(bianceAdapter::fetchLatestPrices));
    var huoBiFuture =
        CompletableFuture.supplyAsync(
            () -> this.fetchLatestPricesByApi(huoBiAdapter::fetchLatestPrices));

    var combinedPriceList =
        List.of(getPriceModelsFromFuture(bianceFuture), getPriceModelsFromFuture(huoBiFuture))
            .stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
    var bestPriceMap = groupBestPriceBySymbol(combinedPriceList);
    priceRepo.saveAll(bestPriceMap.values());
  }

  private List<PriceModel> fetchLatestPricesByApi(Supplier<List<PriceModel>> fetchPrices) {
    var pricesModels = fetchPrices.get();

    return pricesModels.stream().collect(Collectors.toList());
  }

  private List<PriceModel> getPriceModelsFromFuture(
      CompletableFuture<List<PriceModel>> priceModelfuture) {
    try {
      return priceModelfuture.get(timeout, TimeUnit.SECONDS);
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }

  private Map<String, Price> groupBestPriceBySymbol(List<PriceModel> priceModels) {
    var priceMap = new HashMap<String, Price>();
    for (var priceModel : priceModels) {
      var symbol = priceModel.getSymbol();
      if (!priceMap.containsKey(symbol)) {
        priceMap.put(symbol, PriceMapper.INSTANCE.priceModelToPrice(priceModel));
        continue;
      }
      var price = priceMap.get(symbol);
      price.setAsk(price.getAsk().min(priceModel.getAsk()));
      price.setBid(price.getBid().min(priceModel.getBid()));
    }
    return priceMap;
  }
}
