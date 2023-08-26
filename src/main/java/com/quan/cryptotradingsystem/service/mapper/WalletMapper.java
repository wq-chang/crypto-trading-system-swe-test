package com.quan.cryptotradingsystem.service.mapper;

import com.quan.cryptotradingsystem.entity.WalletBalance;
import com.quan.cryptotradingsystem.model.WalletBalanceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {

  WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

  @Mapping(source = "supportedCrypto.code", target = "cryptoCode")
  @Mapping(source = "supportedCrypto.name", target = "cryptoName")
  @Mapping(
      target = "balance",
      expression = "java(walletBalance.getBalance().stripTrailingZeros().toPlainString())")
  WalletBalanceModel walletBalanceToWalletBalanceModel(WalletBalance walletBalance);
}
