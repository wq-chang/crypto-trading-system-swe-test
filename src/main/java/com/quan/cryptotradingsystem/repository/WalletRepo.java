package com.quan.cryptotradingsystem.repository;

import com.quan.cryptotradingsystem.entity.Wallet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet, Long> {

  Optional<Wallet> findByUserId(long userId);
}
