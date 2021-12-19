package com.calculator.coin.service.imp;

import com.calculator.coin.dto.toCoin.CurrencyToCoinRequestDto;
import com.calculator.coin.dto.toCoin.CurrencyToCoinResponseDto;
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
import java.util.Locale;
import java.util.Map;

/**
 * CurrencyToCoinService class.
 * Calculates coin amount with coin/currency data.
 *
 * @author Cagri Zeki
 */
@Component
public class CurrencyToCoinService implements CoinService<CurrencyToCoinRequestDto, CurrencyToCoinResponseDto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyToCoinService.class);
    private CoinDataService coinDataService;
    private CoinRepository coinRepository;

    public CurrencyToCoinService(CoinDataService coinDataService, CoinRepository coinRepository) {
        this.coinDataService = coinDataService;
        this.coinRepository = coinRepository;
    }

    @Override
    public CurrencyToCoinResponseDto calculate(CurrencyToCoinRequestDto requestDto) throws Exception {
        EnumRequestType requestType = EnumRequestType.valueOf(requestDto.getCoinType() + Constants.DELIMITER + requestDto.getCurrencyType());

        Map<String, String> coinDataMap = coinDataService.getCoinData();
        float result = requestDto.getCurrencyAmount() / Float.parseFloat(coinDataMap.get(requestType.getNameStr()));
        CurrencyToCoinResponseDto currencyToCoinResponseDto = new CurrencyToCoinResponseDto();
        currencyToCoinResponseDto.setCoinAmount(result);

        //create coin entity and save to db
        CoinEntity coinEntity = createEntity(result, requestDto);
        coinRepository.save(coinEntity);
        LOGGER.info(coinEntity.toString());

        return currencyToCoinResponseDto;
    }

    private CoinEntity createEntity(float result, CurrencyToCoinRequestDto requestDto){
        CoinEntity coinEntity = new CoinEntity();
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.CANADA);
        Date date = new Date();

        coinEntity.setInputType(requestDto.getCurrencyType());
        coinEntity.setInputAmount(requestDto.getCurrencyAmount());
        coinEntity.setOutputType(requestDto.getCoinType());
        coinEntity.setOutputAmount(result);
        coinEntity.setDate(dateFormat.format(date));
        return coinEntity;
    }
}