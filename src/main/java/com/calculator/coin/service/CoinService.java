package com.calculator.coin.service;

import org.springframework.stereotype.Service;

/**
 * CoinService interface.
 * Provides interface for different calculations.
 *
 * @author Cagri Zeki
 */
@Service
public interface CoinService<T, S> {
    S calculate(T request) throws Exception;
}
