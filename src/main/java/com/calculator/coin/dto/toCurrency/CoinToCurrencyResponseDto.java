package com.calculator.coin.dto.toCurrency;

/**
 * CoinToCurrencyResponseDto class for rest response data returned to clients.
 *
 * @author Cagri Zeki
 */
public class CoinToCurrencyResponseDto {
    private float currencyAmount;

    public float getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(float currencyAmount) {
        this.currencyAmount = currencyAmount;
    }
}