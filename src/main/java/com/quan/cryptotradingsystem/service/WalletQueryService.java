package com.quan.cryptotradingsystem.service;

import com.quan.cryptotradingsystem.model.WalletBalanceModel;
import java.util.List;

public interface WalletQueryService {
  List<WalletBalanceModel> getWalletBalances();
}
