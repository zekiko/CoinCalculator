package com.calculator.coin;

import com.calculator.coin.dto.toCoin.CurrencyToCoinRequestDto;
import com.calculator.coin.dto.toCoin.CurrencyToCoinResponseDto;
import com.calculator.coin.dto.toCurrency.CoinToCurrencyRequestDto;
import com.calculator.coin.dto.toCurrency.CoinToCurrencyResponseDto;
import com.calculator.coin.helper.HelperMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@TestPropertySource("/application.properties")
@SpringBootTest
class IntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private HelperMethods helperMethods;

    @BeforeEach
    void setUp() {
        helperMethods = new HelperMethods();
    }

    ///////////////////////CURRENCY TO COIN TESTS\\\\\\\\\\\\\\\\\\\\\\\\
    @Test
    @DisplayName("USD to BTC calculator test")
    public void givenUSD_whenExpectedBTC_thenReturnBTCAmount() throws Exception {
        CurrencyToCoinRequestDto requestDto = new CurrencyToCoinRequestDto();
        requestDto.setCurrencyAmount(2000);
        requestDto.setCoinType("BTC");
        requestDto.setCurrencyType("USD");

        MvcResult result = mvcPerformer(requestDto, "/api/calculate/coin");
        CurrencyToCoinResponseDto actualResponseBody = objectMapper.readValue(result.getResponse().getContentAsString(), CurrencyToCoinResponseDto.class);

        Map<String, String> dataMap = helperMethods.getDataMap();
        float expected = requestDto.getCurrencyAmount() / Float.parseFloat(dataMap.get("BTC-USD"));

        assertEquals(actualResponseBody.getCoinAmount(), expected);
    }

    @Test
    @DisplayName("EUR to BTC calculator test")
    public void givenEUR_whenExpectedBTC_thenReturnBTCAmount() throws Exception {
        CurrencyToCoinRequestDto requestDto = new CurrencyToCoinRequestDto();
        requestDto.setCurrencyAmount(1500);
        requestDto.setCoinType("BTC");
        requestDto.setCurrencyType("EUR");

        MvcResult result = mvcPerformer(requestDto, "/api/calculate/coin");
        CurrencyToCoinResponseDto actualResponseBody = objectMapper.readValue(result.getResponse().getContentAsString(), CurrencyToCoinResponseDto.class);

        Map<String, String> dataMap = helperMethods.getDataMap();
        float expected = requestDto.getCurrencyAmount() / Float.parseFloat(dataMap.get("BTC-EUR"));

        assertEquals(actualResponseBody.getCoinAmount(), expected);
    }

    @Test
    @DisplayName("USD to ETH calculator test")
    public void givenUSD_whenExpectedETH_thenReturnETHAmount() throws Exception {
        CurrencyToCoinRequestDto requestDto = new CurrencyToCoinRequestDto();
        requestDto.setCurrencyAmount(400);
        requestDto.setCoinType("ETH");
        requestDto.setCurrencyType("USD");

        MvcResult result = mvcPerformer(requestDto, "/api/calculate/coin");
        CurrencyToCoinResponseDto actualResponseBody = objectMapper.readValue(result.getResponse().getContentAsString(), CurrencyToCoinResponseDto.class);

        Map<String, String> dataMap = helperMethods.getDataMap();
        float expected = requestDto.getCurrencyAmount() / Float.parseFloat(dataMap.get("ETH-USD"));

        assertEquals(actualResponseBody.getCoinAmount(), expected);
    }

    @Test
    @DisplayName("EUR to ETH calculator test")
    public void givenEUR_whenExpectedETH_thenReturnETHAmount() throws Exception {
        CurrencyToCoinRequestDto requestDto = new CurrencyToCoinRequestDto();
        requestDto.setCurrencyAmount(888);
        requestDto.setCoinType("ETH");
        requestDto.setCurrencyType("EUR");

        MvcResult result = mvcPerformer(requestDto, "/api/calculate/coin");
        CurrencyToCoinResponseDto actualResponseBody = objectMapper.readValue(result.getResponse().getContentAsString(), CurrencyToCoinResponseDto.class);

        Map<String, String> dataMap = helperMethods.getDataMap();
        float expected = requestDto.getCurrencyAmount() / Float.parseFloat(dataMap.get("ETH-EUR"));

        assertEquals(actualResponseBody.getCoinAmount(), expected);
    }

    ///////////////////////COIN TO CURRENCY TESTS\\\\\\\\\\\\\\\\\\\\\\\\
    @Test
    @DisplayName("BTC to USD calculator test")
    public void givenBTC_whenExpectedUSD_thenReturnUSDAmount() throws Exception {
        CoinToCurrencyRequestDto requestDto = new CoinToCurrencyRequestDto();
        requestDto.setCoinAmount(20);
        requestDto.setCoinType("BTC");
        requestDto.setCurrencyType("USD");

        MvcResult result = mvcPerformer(requestDto, "/api/calculate/currency");
        CoinToCurrencyResponseDto actualResponseBody = objectMapper.readValue(result.getResponse().getContentAsString(), CoinToCurrencyResponseDto.class);

        Map<String, String> dataMap = helperMethods.getDataMap();
        float expected = Float.parseFloat(dataMap.get("BTC-USD")) * requestDto.getCoinAmount();

        assertEquals(actualResponseBody.getCurrencyAmount(), expected);
    }

    @Test
    @DisplayName("BTC to EUR calculator test")
    public void givenBTC_whenExpectedEUR_thenReturnEURAmount() throws Exception {
        CoinToCurrencyRequestDto requestDto = new CoinToCurrencyRequestDto();
        requestDto.setCoinAmount(200);
        requestDto.setCoinType("BTC");
        requestDto.setCurrencyType("EUR");

        MvcResult result = mvcPerformer(requestDto, "/api/calculate/currency");
        CoinToCurrencyResponseDto actualResponseBody = objectMapper.readValue(result.getResponse().getContentAsString(), CoinToCurrencyResponseDto.class);

        Map<String, String> dataMap = helperMethods.getDataMap();
        float expected = Float.parseFloat(dataMap.get("BTC-EUR")) * requestDto.getCoinAmount();

        assertEquals(actualResponseBody.getCurrencyAmount(), expected);
    }

    @Test
    @DisplayName("ETH to USD calculator test")
    public void givenETH_whenExpectedUSD_thenReturnUSDAmount() throws Exception {
        CoinToCurrencyRequestDto requestDto = new CoinToCurrencyRequestDto();
        requestDto.setCoinAmount(100);
        requestDto.setCoinType("ETH");
        requestDto.setCurrencyType("USD");

        MvcResult result = mvcPerformer(requestDto, "/api/calculate/currency");
        CoinToCurrencyResponseDto actualResponseBody = objectMapper.readValue(result.getResponse().getContentAsString(), CoinToCurrencyResponseDto.class);

        Map<String, String> dataMap = helperMethods.getDataMap();
        float expected = Float.parseFloat(dataMap.get("ETH-USD")) * requestDto.getCoinAmount();

        assertEquals(actualResponseBody.getCurrencyAmount(), expected);
    }

    @Test
    @DisplayName("ETH to EUR calculator test")
    public void givenETH_whenExpectedEUR_thenReturnEURAmount() throws Exception {
        CoinToCurrencyRequestDto requestDto = new CoinToCurrencyRequestDto();
        requestDto.setCoinAmount(200);
        requestDto.setCoinType("ETH");
        requestDto.setCurrencyType("EUR");

        MvcResult result = mvcPerformer(requestDto, "/api/calculate/currency");
        CoinToCurrencyResponseDto actualResponseBody = objectMapper.readValue(result.getResponse().getContentAsString(), CoinToCurrencyResponseDto.class);

        Map<String, String> dataMap = helperMethods.getDataMap();
        float expected = Float.parseFloat(dataMap.get("ETH-EUR")) * requestDto.getCoinAmount();

        assertEquals(actualResponseBody.getCurrencyAmount(), expected);
    }

    public MvcResult mvcPerformer(Object object, String endpoint) throws Exception {
        MvcResult result = mvc.perform(post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(object)))
                .andExpect(status().isOk()).
                andReturn();
        return result;
    }
}