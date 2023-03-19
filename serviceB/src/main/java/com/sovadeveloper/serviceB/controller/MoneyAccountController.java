package com.sovadeveloper.serviceB.controller;

import com.sovadeveloper.serviceB.dto.AccountOperationDTO;
import com.sovadeveloper.serviceB.service.MoneyAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moneyAccounts")
@RequiredArgsConstructor
public class MoneyAccountController {
    private final MoneyAccountService moneyAccountService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AccountOperationDTO accountOperation){
        try {
            return ResponseEntity.ok(moneyAccountService.addMoney(accountOperation));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(@RequestBody AccountOperationDTO accountOperation){
        try {
            return ResponseEntity.ok(moneyAccountService.removeMoney(accountOperation));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        try {
            return ResponseEntity.ok(moneyAccountService.viewMoney(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
