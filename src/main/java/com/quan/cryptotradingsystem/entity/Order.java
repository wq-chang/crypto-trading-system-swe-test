package com.quan.cryptotradingsystem.entity;

import com.quan.cryptotradingsystem.enumeration.Action;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ref_id", nullable = false, unique = true)
    private UUID refId;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "from_wallet_balance_id", nullable = false)
    private WalletBalance fromWalletBalance;

    @Column(name = "from_transaction_amount", nullable = false, precision = 36, scale = 18)
    private BigDecimal fromTransactionAmount;

    @Column(name = "from_amount", nullable = false, precision = 36, scale = 18)
    private BigDecimal fromAmount;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "to_wallet_balance_id", nullable = false)
    private WalletBalance toWalletBalance;

    @Column(name = "to_transaction_amount", nullable = false, precision = 36, scale = 18)
    private BigDecimal toTransactionAmount;

    @Column(name = "to_amount", nullable = false, precision = 36, scale = 18)
    private BigDecimal toAmount;

    @Column(name = "price", nullable = false, precision = 36, scale = 18)
    private BigDecimal price;

    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    private Action action;

    @Column(name = "transaction_date_time", nullable = false)
    private LocalDateTime transactionDateTime;

    public Order() {
    }

    public Order(
            WalletBalance fromWalletBalance,
            BigDecimal fromTransactionAmount,
            BigDecimal fromAmount,
            WalletBalance toWalletBalance,
            BigDecimal toTransactionAmount,
            BigDecimal toAmount,
            BigDecimal price,
            Action action,
            LocalDateTime transactionDateTime) {
        this.refId = UUID.randomUUID();
        this.fromWalletBalance = fromWalletBalance;
        this.fromTransactionAmount = fromTransactionAmount;
        this.fromAmount = fromAmount;
        this.toWalletBalance = toWalletBalance;
        this.toTransactionAmount = toTransactionAmount;
        this.toAmount = toAmount;
        this.price = price;
        this.action = action;
        this.transactionDateTime = transactionDateTime;
    }
}
