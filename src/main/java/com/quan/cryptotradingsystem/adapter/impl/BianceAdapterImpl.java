package com.quan.cryptotradingsystem.adapter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.cryptotradingsystem.adapter.PriceAdapter;
import com.quan.cryptotradingsystem.model.BiancePriceModel;
import com.quan.cryptotradingsystem.model.PriceModel;
import com.quan.cryptotradingsystem.service.mapper.PriceMapper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("biance")
public class BianceAdapterImpl implements PriceAdapter {

    @Value("${app.api.biance.url}")
    private String url;

    private final WebClient client;

    @Autowired
    public BianceAdapterImpl(WebClient.Builder clientBuilder) {
        this.client = clientBuilder.build();
    }

    @Override
    public List<PriceModel> fetchLatestPrices() {
        var monoObjects = this.client
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object[].class);
        var objects = monoObjects.block();
        var objectMapper = new ObjectMapper();

        return Arrays.stream(objects)
                .map(o -> objectMapper.convertValue(o, BiancePriceModel.class))
                .map(PriceMapper.INSTANCE::biancePriceToPriceModel)
                .collect(Collectors.toList());
    }
}
