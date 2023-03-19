package com.sovadeveloper.serviceA.client;

import com.sovadeveloper.serviceA.dto.AccountOperationDTO;
import com.sovadeveloper.serviceA.dto.MoneyAccountDTO;
import com.sovadeveloper.serviceA.dto.MoneyAccountViewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "moneyAccountClient", url = "${client.service_b.url}/moneyAccounts")
public interface MoneyAccountClient {
    @PostMapping("/add")
    MoneyAccountDTO addMoney(@RequestBody AccountOperationDTO accountOperation);

    @PostMapping("/remove")
    MoneyAccountDTO removeMoney(@RequestBody AccountOperationDTO accountOperation);

    @GetMapping("/{id}")
    MoneyAccountViewDTO viewMoney(@PathVariable Long id);
}
