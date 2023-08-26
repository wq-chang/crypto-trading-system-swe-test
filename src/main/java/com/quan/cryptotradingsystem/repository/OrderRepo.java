package com.quan.cryptotradingsystem.repository;

import com.quan.cryptotradingsystem.entity.Order;
import com.quan.cryptotradingsystem.entity.WalletBalance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findAllByFromWalletBalanceInOrToWalletBalanceIn(
            List<WalletBalance> fromWalletBalance, List<WalletBalance> toWalletBallance);
}
