package com.sovadeveloper.serviceA.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyAccountDTO {
    private Long id;

    private double redDollar;

    private double greenDollar;

    private double blueDollar;

    private DriverDTO driver;
}
