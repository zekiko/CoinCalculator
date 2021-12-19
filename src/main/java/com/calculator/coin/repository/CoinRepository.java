package com.calculator.coin.repository;

import com.calculator.coin.model.CoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<CoinEntity, Long> {

}