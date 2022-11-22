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
public class FruitMonthPriceResponse implements Serializable {

	private long id;
	private String fruit;
	private String month;
	private String fmp;
	private String environment;
}
