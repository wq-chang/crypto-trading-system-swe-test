package com.quan.cryptotradingsystem.service;

import com.quan.cryptotradingsystem.model.OrderHistoryModel;
import java.util.List;

public interface OrderQueryService {

    List<OrderHistoryModel> getOrderHistories();
}
