package com.calculator.coin.service.restClient;

import com.calculator.coin.utils.Constants;
import com.calculator.coin.utils.EnumRequestType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * CoinDataService class.
 * Applies rest calls to blockchain.com for getting real data.
 * Writes data to coinData map.
 *
 * @author Cagri Zeki
 */
@EnableScheduling
@Service
public class CoinDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoinDataService.class);

    private Map<String, String> coinData;
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 10000)//milliseconds
    public void applyRestRequest() throws Exception {
        URI uri = null;
        ResponseEntity<String> responseEntity = null;
        for (EnumRequestType request : EnumRequestType.values()) {
            uri = new URI(request.getUrl());
            responseEntity = restTemplate.getForEntity(uri, String.class);
            Map<String, String> tempMap = null;
            try {
                tempMap = convertResponseBodyToHashMap(responseEntity.getBody());
            } catch (Exception e) {
                LOGGER.error(Constants.REST_REQUEST_ERROR);
                e.printStackTrace();
            }
            coinData.put(tempMap.get(Constants.SYMBOL), tempMap.get(Constants.PRICE));
        }

        LOGGER.info(String.valueOf(coinData));
    }

    private Map<String, String> convertResponseBodyToHashMap(String responseBody) throws Exception {
        return (Map<String, String>) Optional.ofNullable(
                new Gson().fromJson(
                        responseBody, new TypeToken<HashMap<String, String>>() {
                        }.getType()
                )
        ).orElseThrow(() -> new Exception(Constants.REST_REQUEST_ERROR));
    }

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();
        this.coinData = new HashMap<>();
        try {
            applyRestRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getCoinData() {
        return coinData;
    }

    public void setCoinData(Map<String, String> coinData) {
        this.coinData = coinData;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}