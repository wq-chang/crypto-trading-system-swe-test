package com.quan.cryptotradingsystem.service.mapper;

import com.quan.cryptotradingsystem.entity.Price;
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
  @Mapping(source = "askPrice", target = "ask")
  PriceModel biancePriceToPriceModel(BiancePriceModel biancePriceModel);

  @Mapping(target = "symbol", expression = "java(huoBiPriceModel.getSymbol().toUpperCase())")
  PriceModel huoBiPriceToPriceModel(HuoBiPriceModel huoBiPriceModel);

  Price priceModelToPrice(PriceModel priceModel);

  PriceModel priceToPriceModel(Price price);
}
