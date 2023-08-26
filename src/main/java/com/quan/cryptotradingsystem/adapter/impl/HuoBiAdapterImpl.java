package com.quan.cryptotradingsystem.adapter.impl;

import com.quan.cryptotradingsystem.adapter.PriceAdapter;
import com.quan.cryptotradingsystem.model.HuoBiResponseModel;
import com.quan.cryptotradingsystem.model.PriceModel;
import com.quan.cryptotradingsystem.service.mapper.PriceMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("huoBi")
public class HuoBiAdapterImpl implements PriceAdapter {

    @Value("${app.huoBi.url}")
    private String url;

    private final WebClient client;

    @Autowired
    public HuoBiAdapterImpl(WebClient.Builder clientBuilder) {
        this.client = clientBuilder.build();
    }

    @Override
    public List<PriceModel> fetchLatestPrices() {
        var monoObjects = this.client
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(HuoBiResponseModel.class);
        var huobiResponse = monoObjects.block();

        return huobiResponse.getData().stream()
                .map(PriceMapper.INSTANCE::huoBiPriceToPriceModel)
                .collect(Collectors.toList());
    }
}
