package com.quan.cryptotradingsystem.adapter;

import com.quan.cryptotradingsystem.model.BiancePriceModel;
import java.util.List;

public interface BianceAdapter {
  List<BiancePriceModel> fetchLatestPrices();
}
