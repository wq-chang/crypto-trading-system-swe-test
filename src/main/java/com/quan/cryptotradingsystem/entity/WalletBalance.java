package com.quan.cryptotradingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "wallet_balances", uniqueConstraints = @UniqueConstraint(columnNames = { "wallet_id",
        "supported_crypto_id" }))
public class WalletBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "balance", nullable = false, precision = 36, scale = 18)
    BigDecimal balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "supported_crypto_id", nullable = false)
    private SupportedCrypto supportedCrypto;

    @OneToMany(mappedBy = "fromWalletBalance", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<Order> fromOrders;

    @OneToMany(mappedBy = "toWalletBalance", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<Order> toOrders;

    public WalletBalance() {
    }

    public WalletBalance(Wallet wallet, SupportedCrypto supportedCrypto) {
        this.wallet = wallet;
        this.supportedCrypto = supportedCrypto;
        this.balance = new BigDecimal(0);
        this.fromOrders = new ArrayList<>();
        this.toOrders = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public SupportedCrypto getSupportedCrypto() {
        return supportedCrypto;
    }

    public void setSupportedCrypto(SupportedCrypto supportedCrypto) {
        this.supportedCrypto = supportedCrypto;
    }

    public List<Order> getFromOrders() {
        return fromOrders;
    }

    public void setFromOrders(List<Order> fromOrders) {
        this.fromOrders = fromOrders;
    }

    public List<Order> getToOrders() {
        return toOrders;
    }

    public void setToOrders(List<Order> toOrders) {
        this.toOrders = toOrders;
    }
}
