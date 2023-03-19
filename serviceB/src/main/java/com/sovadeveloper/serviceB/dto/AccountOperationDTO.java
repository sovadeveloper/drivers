package com.sovadeveloper.serviceB.dto;

import com.sovadeveloper.serviceB.entity.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountOperationDTO {
    private Long moneyAccountId;

    private Money money;

    private double amount;
}
