package com.quan.cryptotradingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "crypto_pairs")
public class CryptoPair {

    @Id
    @Column(name = "symbol")
    private String symbol;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "base_crypto_id", nullable = false)
    private SupportedCrypto baseCrypto;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "pair_crypto_id", nullable = false)
    private SupportedCrypto pairCrypto;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public SupportedCrypto getBaseCrypto() {
        return baseCrypto;
    }

    public void setBaseCrypto(SupportedCrypto baseCrypto) {
        this.baseCrypto = baseCrypto;
    }

    public SupportedCrypto getPairCrypto() {
        return pairCrypto;
    }

    public void setPairCrypto(SupportedCrypto pairCrypto) {
        this.pairCrypto = pairCrypto;
    }
}
