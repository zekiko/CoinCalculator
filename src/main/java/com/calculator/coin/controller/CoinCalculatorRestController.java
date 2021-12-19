package com.calculator.coin.controller;

import com.calculator.coin.dto.toCoin.CurrencyToCoinRequestDto;
import com.calculator.coin.dto.toCoin.CurrencyToCoinResponseDto;
import com.calculator.coin.dto.toCurrency.CoinToCurrencyRequestDto;
import com.calculator.coin.dto.toCurrency.CoinToCurrencyResponseDto;
import com.calculator.coin.model.CoinEntity;
import com.calculator.coin.repository.CoinRepository;
import com.calculator.coin.service.factory.CoinCalculatorFactory;
import com.calculator.coin.service.CoinService;
import com.calculator.coin.service.imp.CoinToCurrencyService;
import com.calculator.coin.service.imp.CurrencyToCoinService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * CoinCalculatorRestController class for rest requests from clients.
 *
 * @author Cagri Zeki
 */
@RestController
@RequestMapping("/api")
@Api(value="CoinCalculator Rest Service")
public class CoinCalculatorRestController {

    private CoinRepository coinRepository;
    private CoinCalculatorFactory coinCalculatorFactory;

    public CoinCalculatorRestController(CoinRepository coinRepository, CoinCalculatorFactory coinCalculatorFactory) {
        this.coinRepository = coinRepository;
        this.coinCalculatorFactory = coinCalculatorFactory;
    }

    @PostMapping("/calculate/coin")
    public CurrencyToCoinResponseDto currencyToCoin(@Valid @RequestBody CurrencyToCoinRequestDto currencyToCoinRequestDto) {

        CoinService coinService = coinCalculatorFactory
                .getCalculatorService(CurrencyToCoinService.class)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));

        CurrencyToCoinResponseDto currencyToCoinResponseDto = null;
        try {
            currencyToCoinResponseDto = (CurrencyToCoinResponseDto) coinService.calculate(currencyToCoinRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currencyToCoinResponseDto;
    }

    @PostMapping("/calculate/currency")
    public CoinToCurrencyResponseDto coinToCurrency(@Valid @RequestBody CoinToCurrencyRequestDto requestDto) {

        CoinService coinService = coinCalculatorFactory
                .getCalculatorService(CoinToCurrencyService.class)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));

        CoinToCurrencyResponseDto coinToCurrencyResponseDto = null;
        try {
            coinToCurrencyResponseDto = (CoinToCurrencyResponseDto) coinService.calculate(requestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coinToCurrencyResponseDto;
    }

    @GetMapping("/coins")
    public List<CoinEntity> getAllCoins() {
        return coinRepository.findAll();
    }

}

