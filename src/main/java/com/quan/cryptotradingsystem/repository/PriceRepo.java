package com.quan.cryptotradingsystem.repository;

import com.quan.cryptotradingsystem.entity.Price;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepo extends JpaRepository<Price, Long> {

    Optional<Price> findBySymbol(String symbol);
}
