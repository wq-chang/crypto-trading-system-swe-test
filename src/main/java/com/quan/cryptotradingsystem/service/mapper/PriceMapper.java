package com.quan.cryptotradingsystem.service.mapper;

import com.quan.cryptotradingsystem.model.BiancePriceModel;
import com.quan.cryptotradingsystem.model.HuoBiPriceModel;
import com.quan.cryptotradingsystem.model.PriceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {

  PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

  @Mapping(source = "bidPrice", target = "bid")
  @Mapping(source = "bidQty", target = "bidSize")
  @Mapping(source = "askPrice", target = "ask")
  @Mapping(source = "askQty", target = "askSize")
  PriceModel biancePriceToPriceModel(BiancePriceModel biancePriceModel);

  PriceModel huoBiPriceToPriceModel(HuoBiPriceModel biancePriceModel);
}
