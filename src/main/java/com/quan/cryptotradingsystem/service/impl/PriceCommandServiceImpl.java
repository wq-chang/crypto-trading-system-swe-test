package com.quan.cryptotradingsystem.service.impl;

import com.quan.cryptotradingsystem.adapter.PriceAdapter;
import com.quan.cryptotradingsystem.model.PriceModel;
import com.quan.cryptotradingsystem.service.PriceCommandService;
import com.quan.cryptotradingsystem.service.mapper.PriceMapper;
import com.quan.cryptotradingsystem.service.repository.PriceRepo;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PriceCommandServiceImpl implements PriceCommandService {

    @Value("#{'${app.allowed-symbols}'.split(',')}")
    private List<String> allowedSymbols;

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
        CompletableFuture.runAsync(
                () -> this.fetchAndSaveLatestPricesByApi(bianceAdapter::fetchLatestPrices));
        CompletableFuture.runAsync(
                () -> this.fetchAndSaveLatestPricesByApi(huoBiAdapter::fetchLatestPrices));
    }

    private void fetchAndSaveLatestPricesByApi(Supplier<List<PriceModel>> fetchPrices) {
        var pricesModels = fetchPrices.get();

        var filteredPrices = pricesModels.stream()
                .filter(p -> allowedSymbols.contains(p.getSymbol().toUpperCase()))
                .map(PriceMapper.INSTANCE::priceModelToPrice)
                .collect(Collectors.toList());

        priceRepo.saveAll(filteredPrices);
    }
}
