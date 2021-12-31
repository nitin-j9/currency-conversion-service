package com.spring.cloud.in28minutes.bean;

import java.math.BigDecimal;

public class CurrencyConversionBean {

	private int id;
	private String from;
	private String to;
	private BigDecimal exchangeValue;
	private BigDecimal quantity;
	private BigDecimal calculatedValue;
	private int port;

	public CurrencyConversionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrencyConversionBean(int id, String from, String to, BigDecimal exchangeValue, BigDecimal quantity,
			BigDecimal calculatedValue, int port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.exchangeValue = exchangeValue;
		this.quantity = quantity;
		this.calculatedValue = calculatedValue;
		this.port = port;
	}

	public CurrencyConversionBean(String from, String to, BigDecimal exchangeValue, BigDecimal quantity,
			BigDecimal calculatedValue, int port) {
		super();
		this.from = from;
		this.to = to;
		this.exchangeValue = exchangeValue;
		this.quantity = quantity;
		this.calculatedValue = calculatedValue;
		this.port = port;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getExchangeValue() {
		return exchangeValue;
	}

	public void setExchangeValue(BigDecimal exchangeValue) {
		this.exchangeValue = exchangeValue;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getCalculatedValue() {
		return calculatedValue;
	}

	public void setCalculatedValue(BigDecimal calculatedValue) {
		this.calculatedValue = calculatedValue;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "CurrencyConversionBean [id=" + id + ", from=" + from + ", to=" + to + ", exchangeValue=" + exchangeValue
				+ ", quantity=" + quantity + ", calculatedValue=" + calculatedValue + ", port=" + port + "]";
	}

}
