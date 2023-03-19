package com.sovadeveloper.serviceB.dto;

import com.sovadeveloper.serviceB.entity.Driver;
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
