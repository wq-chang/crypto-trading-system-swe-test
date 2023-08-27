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

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "from_wallet_balance_id", nullable = false)
    private WalletBalance fromWalletBalance;

    @Column(name = "from_transaction_amount", nullable = false, precision = 36, scale = 18)
    private BigDecimal fromTransactionAmount;

    @Column(name = "from_balance", nullable = false, precision = 36, scale = 18)
    private BigDecimal fromBalance;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "to_wallet_balance_id", nullable = false)
    private WalletBalance toWalletBalance;

    @Column(name = "to_transaction_amount", nullable = false, precision = 36, scale = 18)
    private BigDecimal toTransactionAmount;

    @Column(name = "to_balance", nullable = false, precision = 36, scale = 18)
    private BigDecimal toBalance;

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
            String symbol,
            WalletBalance fromWalletBalance,
            BigDecimal fromTransactionAmount,
            BigDecimal fromBalance,
            WalletBalance toWalletBalance,
            BigDecimal toTransactionAmount,
            BigDecimal toBalance,
            BigDecimal price,
            Action action,
            LocalDateTime transactionDateTime) {
        this.refId = UUID.randomUUID();
        this.symbol = symbol;
        this.fromWalletBalance = fromWalletBalance;
        this.fromTransactionAmount = fromTransactionAmount;
        this.fromBalance = fromBalance;
        this.toWalletBalance = toWalletBalance;
        this.toTransactionAmount = toTransactionAmount;
        this.toBalance = toBalance;
        this.price = price;
        this.action = action;
        this.transactionDateTime = transactionDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getRefId() {
        return refId;
    }

    public void setRefId(UUID refId) {
        this.refId = refId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public WalletBalance getFromWalletBalance() {
        return fromWalletBalance;
    }

    public void setFromWalletBalance(WalletBalance fromWalletBalance) {
        this.fromWalletBalance = fromWalletBalance;
    }

    public BigDecimal getFromTransactionAmount() {
        return fromTransactionAmount;
    }

    public void setFromTransactionAmount(BigDecimal fromTransactionAmount) {
        this.fromTransactionAmount = fromTransactionAmount;
    }

    public BigDecimal getFromBalance() {
        return fromBalance;
    }

    public void setFromBalance(BigDecimal fromBalance) {
        this.fromBalance = fromBalance;
    }

    public WalletBalance getToWalletBalance() {
        return toWalletBalance;
    }

    public void setToWalletBalance(WalletBalance toWalletBalance) {
        this.toWalletBalance = toWalletBalance;
    }

    public BigDecimal getToTransactionAmount() {
        return toTransactionAmount;
    }

    public void setToTransactionAmount(BigDecimal toTransactionAmount) {
        this.toTransactionAmount = toTransactionAmount;
    }

    public BigDecimal getToBalance() {
        return toBalance;
    }

    public void setToBalance(BigDecimal toBalance) {
        this.toBalance = toBalance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}
