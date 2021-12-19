package com.calculator.coin.repository;

import com.calculator.coin.model.CoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CoinRepository class for repository responsibilities.
 * Supplies abstractions for transactions.
 *
 * @author Cagri Zeki
 */
@Repository
public interface CoinRepository extends JpaRepository<CoinEntity, Long> {

}