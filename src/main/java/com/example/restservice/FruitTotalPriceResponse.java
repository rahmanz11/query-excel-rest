package com.example.restservice;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FruitTotalPriceResponse implements Serializable {

	private long id;
	private String fruit;
	private String month;
	private float fmp;
	private int quantity;
	private double totalPrice;
	private String environment;
}
