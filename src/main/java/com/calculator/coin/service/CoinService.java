package com.calculator.coin.service;

import org.springframework.stereotype.Service;

@Service
public interface CoinService<T, S> {
    S calculate(T request) throws Exception;
}
