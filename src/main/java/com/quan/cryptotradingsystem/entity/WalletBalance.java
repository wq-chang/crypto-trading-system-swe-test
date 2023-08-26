package com.quan.cryptotradingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "wallet_balances")
public class WalletBalance {

  @EmbeddedId private WalletBalanceId id;

  @Column(name = "balance", precision = 30, scale = 15)
  BigDecimal balance;

  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("walletId")
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "wallet_id", nullable = false)
  private Wallet wallet;

  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("supportedCryptoId")
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "supported_crypto_id", nullable = false)
  private SupportedCrypto supportedCrypto;

  public WalletBalanceId getId() {
    return id;
  }

  public void setId(WalletBalanceId id) {
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
}
