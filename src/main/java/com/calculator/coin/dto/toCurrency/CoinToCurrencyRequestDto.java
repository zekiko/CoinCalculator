package com.calculator.coin.dto.toCurrency;

import com.calculator.coin.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@Api(value="Coin to currency calculation request dto")
public class CoinToCurrencyRequestDto {

    @NotNull
    @Pattern(regexp= Constants.COIN_TYPES, message = Constants.INVALID_COIN_MSG)
    @ApiModelProperty(value="Coin Type")
    private String coinType;

    @NotNull
    @ApiModelProperty(value="Coin Amount")
    private float coinAmount;

    @NotNull
    @Pattern(regexp= Constants.CURRENCY_TYPES, message = Constants.INVALID_CURRENCY_MSG)
    @ApiModelProperty(value="Currency Type")
    private String currencyType;

    public CoinToCurrencyRequestDto(){}

    public CoinToCurrencyRequestDto(String coinType, float coinAmount, String currencyType) {
        this.coinType = coinType;
        this.coinAmount = coinAmount;
        this.currencyType = currencyType;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public float getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(float coinAmount) {
        this.coinAmount = coinAmount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
}
