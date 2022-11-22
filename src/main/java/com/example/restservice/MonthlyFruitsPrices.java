package com.example.restservice;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonthlyFruitsPrices implements Serializable {
    private String fruit;
    private float jan;
    private float feb;
    private float mar;
    private float apr;
    private float may;
    private float jun;
    private float jul;
    private float aug;
    private float sep;
    private float oct;
    private float nov;
    private float dec;
}
