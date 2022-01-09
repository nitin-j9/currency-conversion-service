package com.spring.cloud.in28minutes.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.cloud.in28minutes.bean.CurrencyConversionBean;
import com.spring.cloud.in28minutes.feign.CurrencyExchangeServiceFeignClient;

@RestController
public class CurrencyConverterController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CurrencyExchangeServiceFeignClient currencyExchangeFeign;

	@GetMapping(path = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity convertCurrency(@PathVariable Map<String, String> pathVariables) {
		String from = pathVariables.get("from");
		String to = pathVariables.get("to");
		BigDecimal quantity = BigDecimal.valueOf(Long.parseLong(pathVariables.get("quantity")));

		// Rest API Call Using RestTemplate
		Map<String, String> uriPathVars = new HashMap<String, String>();
		uriPathVars.put("from", from);
		uriPathVars.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriPathVars);
		CurrencyConversionBean currencyConversionBean = responseEntity.getBody();
		currencyConversionBean.setQuantity(quantity);
		currencyConversionBean.setCalculatedValue(calculate(currencyConversionBean));
		return ResponseEntity.ok(currencyConversionBean);
	}

	private BigDecimal calculate(CurrencyConversionBean currencyConversionBean) {
		return currencyConversionBean.getExchangeValue().multiply(currencyConversionBean.getQuantity());
	}

	@GetMapping(path = "/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	@HystrixCommand(fallbackMethod = "getFallbackConvertCurrencyUsignFeign")
	public ResponseEntity convertCurrencyUsignFeign(@PathVariable Map<String, String> pathVariables) {
		String from = pathVariables.get("from");
		String to = pathVariables.get("to");
		BigDecimal quantity = BigDecimal.valueOf(Long.parseLong(pathVariables.get("quantity")));

		// Rest API Call Using Feign Client
		CurrencyConversionBean currencyConversionBean = currencyExchangeFeign.getCurrencyExchange(from, to);
		currencyConversionBean.setQuantity(quantity);
		currencyConversionBean.setCalculatedValue(calculate(currencyConversionBean));
		logger.info("{}", currencyConversionBean);
		return ResponseEntity.ok(currencyConversionBean);
	}

	public ResponseEntity getFallbackConvertCurrencyUsignFeign(@PathVariable Map<String, String> pathVariables) {
		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(0, "test", "test",
				BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), 0000);
		return ResponseEntity.ok(currencyConversionBean);
	}

}
