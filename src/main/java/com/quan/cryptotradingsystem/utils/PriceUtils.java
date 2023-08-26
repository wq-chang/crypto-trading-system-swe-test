package com.quan.cryptotradingsystem.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PriceUtils {

    private PriceUtils() {
    }

    public static BigDecimal convertPrice(BigDecimal price) {
        return new BigDecimal(1).divide(price, 18, RoundingMode.DOWN);
    }
}
