package com.calculator.coin.dto.toCoin;

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