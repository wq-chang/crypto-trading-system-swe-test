package com.quan.cryptotradingsystem.model;

import com.quan.cryptotradingsystem.enumeration.Action;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderHistoryModel {

    private UUID refId;
    private String symbol;
    private String fromCryptoCode;
    private BigDecimal fromTransactionAmount;
    private BigDecimal fromBalance;
    private String toCryptoCode;
    private BigDecimal toTransactionAmount;
    private BigDecimal toBalance;
    private BigDecimal price;
    private Action action;
    private LocalDateTime transactionDateTime;

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

    public String getFromCryptoCode() {
        return fromCryptoCode;
    }

    public void setFromCryptoCode(String fromCryptoCode) {
        this.fromCryptoCode = fromCryptoCode;
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

    public String getToCryptoCode() {
        return toCryptoCode;
    }

    public void setToCryptoCode(String toCryptoCode) {
        this.toCryptoCode = toCryptoCode;
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

    public void setToBalance(BigDecimal toAmount) {
        this.toBalance = toAmount;
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
