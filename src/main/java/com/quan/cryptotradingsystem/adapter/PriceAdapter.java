package com.quan.cryptotradingsystem.adapter;

import com.quan.cryptotradingsystem.model.PriceModel;
import java.util.List;

public interface PriceAdapter {

    List<PriceModel> fetchLatestPrices();
}
