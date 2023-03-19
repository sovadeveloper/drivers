package com.sovadeveloper.serviceA.controller;

import com.sovadeveloper.serviceA.client.MoneyAccountClient;
import com.sovadeveloper.serviceA.dto.AccountOperationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moneyAccounts")
@RequiredArgsConstructor
@Tag(name="Денежные счета", description="Контроллер отвечающий за работу с сущностями MoneyAccount")
public class MoneyAccountController {
    private final MoneyAccountClient moneyAccountClient;

    @Operation(
            summary = "Пополнение счета",
            description = "Позволяет пополнить счет на определенную сумму, в определенной валюте"
    )
    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody @Parameter(description = "Объект AccountOperationDTO") AccountOperationDTO accountOperation){
        try {
            return ResponseEntity.ok(moneyAccountClient.addMoney(accountOperation));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Снятие денег со счета",
            description = "Позволяет снять деньги со счета на определенную сумму, в определенной валюте"
    )
    @PostMapping("/remove")
    public ResponseEntity<?> remove(
            @RequestBody @Parameter(description = "Объект AccountOperationDTO") AccountOperationDTO accountOperation){
        try {
            return ResponseEntity.ok(moneyAccountClient.removeMoney(accountOperation));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Просмотр счета",
            description = "Позволяет просмотреть состояние счета, показывая состояние во всех видах валют," +
                    " как по отдельности, так и в конвретированом варианте"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable @Parameter(description = "Идентификатор MoneyAccount") Long id){
        try {
            return ResponseEntity.ok(moneyAccountClient.viewMoney(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
