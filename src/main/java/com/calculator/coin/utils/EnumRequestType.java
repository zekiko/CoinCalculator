package com.calculator.coin.utils;

/**
 * EnumRequestType enum.
 *
 * @author Cagri Zeki
 */
public enum EnumRequestType {
    BTC_USD(Constants.BTC_USD_ENDPOINT, "BTC-USD"),
    BTC_EUR(Constants.BTC_EUR_ENDPOINT, "BTC-EUR"),
    ETH_USD(Constants.ETH_USD_ENDPOINT, "ETH-USD"),
    ETH_EUR(Constants.ETH_EUR_ENDPOINT, "ETH-EUR");

    private final String url;
    private final String nameStr;

    EnumRequestType(String url, String nameStr) {
        this.url = url;
        this.nameStr = nameStr;
    }

    public String getUrl() {
        return url;
    }

    public String getNameStr() {
        return nameStr;
    }
}