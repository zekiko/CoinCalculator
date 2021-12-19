package com.calculator.coin.dto.toCoin;

import com.calculator.coin.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * CurrencyToCoinRequestDto class for rest requests based data from clients.
 *
 * @author Cagri Zeki
 */
@Api(value="Currency to coin calculation request dto")
public class CurrencyToCoinRequestDto {

    @Pattern(regexp= Constants.CURRENCY_TYPES, message = Constants.INVALID_CURRENCY_MSG)
    @NotNull
    @ApiModelProperty(value="Currency Type")
    private String currencyType;

    @Min(value = 25, message = Constants.SMALL_PRICE_MSG)
    @Max(value = 5000, message = Constants.BIG_PRICE_MSG)
    @NotNull
    @ApiModelProperty(value="Currency amount")
    private float currencyAmount;

    @Pattern(regexp= Constants.COIN_TYPES, message = Constants.INVALID_COIN_MSG)
    @NotNull
    @ApiModelProperty(value="Coin Type")
    private String coinType;

    public CurrencyToCoinRequestDto(){}

    public CurrencyToCoinRequestDto(String currencyType, String coinType, float currencyAmount) {
        this.currencyType = currencyType;
        this.coinType = coinType;
        this.currencyAmount = currencyAmount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public float getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(float currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }
}
