package com.quan.cryptotradingsystem.service.repository;

import com.quan.cryptotradingsystem.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepo extends JpaRepository<Price, Long> {
}
