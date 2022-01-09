package com.spring.cloud.in28minutes.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.cloud.in28minutes.bean.CurrencyConversionBean;

//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceFeignClient {

	@GetMapping(path = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean getCurrencyExchange(@PathVariable("from") String from, @PathVariable("to") String to);
}
