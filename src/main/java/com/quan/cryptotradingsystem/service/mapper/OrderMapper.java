package com.quan.cryptotradingsystem.service.mapper;

import com.quan.cryptotradingsystem.entity.Order;
import com.quan.cryptotradingsystem.model.OrderHistoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "fromWalletBalance.supportedCrypto.code", target = "fromCryptoCode")
    @Mapping(source = "toWalletBalance.supportedCrypto.code", target = "toCryptoCode")
    OrderHistoryModel orderToOrderHistoryModel(Order order);
}
