package com.quan.cryptotradingsystem.repository;

import com.quan.cryptotradingsystem.entity.CryptoPair;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoPairRepo extends JpaRepository<CryptoPair, Long> {

    Optional<CryptoPair> findBySymbol(String symbol);
}
