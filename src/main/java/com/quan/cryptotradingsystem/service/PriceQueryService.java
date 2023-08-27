package com.quan.cryptotradingsystem.service;

import com.quan.cryptotradingsystem.model.PriceModel;
import java.util.List;

public interface PriceQueryService {

    List<PriceModel> getBestPrices();
}
