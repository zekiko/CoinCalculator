package com.calculator.coin.service.imp;

import com.calculator.coin.dto.toCurrency.CoinToCurrencyRequestDto;
import com.calculator.coin.dto.toCurrency.CoinToCurrencyResponseDto;
import com.calculator.coin.model.CoinEntity;
import com.calculator.coin.repository.CoinRepository;
import com.calculator.coin.service.CoinService;
import com.calculator.coin.service.restClient.CoinDataService;
import com.calculator.coin.utils.Constants;
import com.calculator.coin.utils.EnumRequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class CoinToCurrencyService implements CoinService<CoinToCurrencyRequestDto, CoinToCurrencyResponseDto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoinToCurrencyService.class);
    private CoinDataService coinDataService;
    private CoinRepository coinRepository;

    public CoinToCurrencyService(CoinDataService coinDataService, CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
        this.coinDataService = coinDataService;
    }

    @Override
    public CoinToCurrencyResponseDto calculate(CoinToCurrencyRequestDto requestDto) {
        EnumRequestType requestType = EnumRequestType.valueOf(requestDto.getCoinType() + Constants.DELIMITER + requestDto.getCurrencyType());

        Map<String, String> coinDataMap = coinDataService.getCoinData();
        float result = requestDto.getCoinAmount() * Float.parseFloat(coinDataMap.get(requestType.getNameStr()));
        CoinToCurrencyResponseDto coinToCurrencyResponseDto = new CoinToCurrencyResponseDto();
        coinToCurrencyResponseDto.setCurrencyAmount(result);

        //create coin entity and save to db
        CoinEntity coinEntity = createEntity(result, requestDto);
        coinRepository.save(coinEntity);
        LOGGER.info(coinEntity.toString());

        return coinToCurrencyResponseDto;
    }

    private CoinEntity createEntity(float result, CoinToCurrencyRequestDto requestDto) {
        CoinEntity coinEntity = new CoinEntity();
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = new Date();

        coinEntity.setInputType(requestDto.getCoinType());
        coinEntity.setInputAmount(requestDto.getCoinAmount());
        coinEntity.setOutputType(requestDto.getCurrencyType());
        coinEntity.setOutputAmount(result);
        coinEntity.setDate(dateFormat.format(date));
        return coinEntity;
    }
}
