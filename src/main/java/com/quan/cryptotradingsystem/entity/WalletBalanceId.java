package com.quan.cryptotradingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class WalletBalanceId {

    @Column(name = "wallet_id")
    long walletId;

    @Column(name = "supported_crypto_id")
    long supportedCryptoId;

    public WalletBalanceId(long walletId, long supportedCryptoId) {
        this.walletId = walletId;
        this.supportedCryptoId = supportedCryptoId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (walletId ^ (walletId >>> 32));
        result = prime * result + (int) (supportedCryptoId ^ (supportedCryptoId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WalletBalanceId other = (WalletBalanceId) obj;
        if (walletId != other.walletId)
            return false;
        if (supportedCryptoId != other.supportedCryptoId)
            return false;
        return true;
    }
}
