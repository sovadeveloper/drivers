package com.sovadeveloper.serviceB.service;

import com.sovadeveloper.serviceB.dto.AccountOperationDTO;
import com.sovadeveloper.serviceB.dto.MoneyAccountDTO;
import com.sovadeveloper.serviceB.dto.MoneyAccountViewDTO;
import com.sovadeveloper.serviceB.entity.Money;
import com.sovadeveloper.serviceB.entity.MoneyAccount;

public interface MoneyAccountService {
    MoneyAccountDTO addMoney(AccountOperationDTO accountOperation);
    MoneyAccountDTO removeMoney(AccountOperationDTO accountOperation);
    MoneyAccountViewDTO viewMoney(Long id);
}
