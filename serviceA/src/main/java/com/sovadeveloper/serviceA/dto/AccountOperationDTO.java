package com.sovadeveloper.serviceA.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountOperationDTO {
    private Long moneyAccountId;

    private Money money;

    private double amount;
}
