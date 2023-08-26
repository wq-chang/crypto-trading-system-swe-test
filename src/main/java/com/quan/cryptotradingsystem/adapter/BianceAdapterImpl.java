package com.quan.cryptotradingsystem.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.cryptotradingsystem.model.BiancePriceModel;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BianceAdapterImpl implements BianceAdapter {

  @Value("${app.biance.url}")
  String url;

  private final WebClient client;

  @Autowired
  public BianceAdapterImpl(WebClient.Builder clientBuilder) {
    this.client = clientBuilder.build();
  }

  @Override
  public List<BiancePriceModel> fetchLatestPrices() {
    var monoObjects =
        this.client
            .get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Object[].class)
            .log();
    var objects = monoObjects.block();
    var objectMapper = new ObjectMapper();
    return Arrays.stream(objects)
        .map(o -> objectMapper.convertValue(o, BiancePriceModel.class))
        .collect(Collectors.toList());
  }
}
