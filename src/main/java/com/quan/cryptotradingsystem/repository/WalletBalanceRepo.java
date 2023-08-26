package com.quan.cryptotradingsystem.repository;

import com.quan.cryptotradingsystem.entity.SupportedCrypto;
import com.quan.cryptotradingsystem.entity.Wallet;
import com.quan.cryptotradingsystem.entity.WalletBalance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletBalanceRepo extends JpaRepository<WalletBalance, Long> {

    @Query("FROM WalletBalance wb WHERE wb.wallet = ?1 AND wb.supportedCrypto IN ?2")
    List<WalletBalance> findAllByWalletAndCryptoCodes(
            Wallet wallet, List<SupportedCrypto> cryptoCodes);
}
