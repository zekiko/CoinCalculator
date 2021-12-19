package com.calculator.coin.service.factory;

import com.calculator.coin.service.CoinService;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * CoinCalculatorFactory class implements factory method.
 * Supplies appropriate coin calculator service.
 *
 * @author Cagri Zeki
 */
@Component
public class CoinCalculatorFactory {

    private List<CoinService> coinServices;

    public CoinCalculatorFactory(List<CoinService> coinServices){
        this.coinServices = coinServices;
    };

    public Optional<CoinService> getCalculatorService(Class clazz){
        return coinServices.stream().filter(s -> s.getClass().equals(clazz)).findFirst();
    }

}