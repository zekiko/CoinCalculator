package com.calculator.coin.dto.toCoin;

/**
 * CurrencyToCoinResponseDto class for rest response data returned to clients.
 *
 * @author Cagri Zeki
 */
public class CurrencyToCoinResponseDto {
    private float coinAmount;

    public CurrencyToCoinResponseDto() {
    }

    public float getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(float coinAmount) {
        this.coinAmount = coinAmount;
    }
}