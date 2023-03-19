package com.sovadeveloper.serviceA.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MoneyAccountViewDTO {
    private double redDollar;

    private double greenDollar;

    private double blueDollar;

    private double allSumInRed;

    private double allSumInGreen;

    private double allSumInBlue;
}
