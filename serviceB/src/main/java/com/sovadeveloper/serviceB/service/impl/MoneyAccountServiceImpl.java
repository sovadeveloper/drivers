package com.sovadeveloper.serviceB.service.impl;

import com.sovadeveloper.serviceB.dto.AccountOperationDTO;
import com.sovadeveloper.serviceB.dto.MoneyAccountDTO;
import com.sovadeveloper.serviceB.dto.MoneyAccountViewDTO;
import com.sovadeveloper.serviceB.entity.Money;
import com.sovadeveloper.serviceB.entity.MoneyAccount;
import com.sovadeveloper.serviceB.mapper.MoneyAccountMapper;
import com.sovadeveloper.serviceB.repository.MoneyAccountRepo;
import com.sovadeveloper.serviceB.service.MoneyAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoneyAccountServiceImpl implements MoneyAccountService{

    private final MoneyAccountRepo moneyAccountRepo;
    private final MoneyAccountMapper moneyAccountMapper;
    @Value("${moneyRate.redToGreen}")
    private double redToGreenRate;
    @Value("${moneyRate.greenToBlue}")
    private double greenToBlueRate;
    @Value("${moneyRate.redToBlue}")
    private double redToBlueRate;

    @Override
    public MoneyAccountDTO addMoney(AccountOperationDTO accountOperation) {
        MoneyAccount moneyAccount = moneyAccountRepo.findById(accountOperation.getMoneyAccountId())
                .orElseThrow(() -> new RuntimeException("Данный счет не найден"));
        double amount = Math.abs(accountOperation.getAmount());
        MoneyAccount moneyAccountUpdated = changeSum(moneyAccount, accountOperation.getMoney(), amount,
                "add");
        return moneyAccountMapper.toDto(moneyAccountRepo.save(moneyAccountUpdated));
    }

    @Override
    public MoneyAccountDTO removeMoney(AccountOperationDTO accountOperation) {
        MoneyAccount moneyAccount = moneyAccountRepo.findById(accountOperation.getMoneyAccountId())
                .orElseThrow(() -> new RuntimeException("Данный счет не найден"));
        double amount = Math.abs(accountOperation.getAmount());
        MoneyAccount moneyAccountUpdated = changeSum(moneyAccount, accountOperation.getMoney(), amount,
                "remove");
        return moneyAccountMapper.toDto(moneyAccountRepo.save(moneyAccountUpdated));
    }

    @Override
    public MoneyAccountViewDTO viewMoney(Long id) {
        MoneyAccount moneyAccount = moneyAccountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Данный счет не найден"));
        return MoneyAccountViewDTO
                .builder()
                .redDollar(moneyAccount.getRedDollar())
                .greenDollar(moneyAccount.getGreenDollar())
                .blueDollar(moneyAccount.getBlueDollar())
                .allSumInRed(moneyAccount.getRedDollar() + (moneyAccount.getGreenDollar() / redToGreenRate) +
                        (moneyAccount.getBlueDollar() / redToBlueRate))
                .allSumInGreen(moneyAccount.getGreenDollar() + (moneyAccount.getRedDollar() * redToGreenRate) +
                        (moneyAccount.getBlueDollar() / greenToBlueRate))
                .allSumInBlue(moneyAccount.getBlueDollar() + (moneyAccount.getRedDollar() * redToBlueRate) +
                        (moneyAccount.getGreenDollar() * greenToBlueRate))
                .build();
    }

    private MoneyAccount changeSum(MoneyAccount moneyAccount, Money money, double amount, String operationType){
        if(operationType.equals("add")){
            if(money == Money.BLUE){
                moneyAccount.setBlueDollar(moneyAccount.getBlueDollar() + amount);
            }else if(money == Money.RED){
                moneyAccount.setRedDollar(moneyAccount.getRedDollar() + amount);
            }else if(money == Money.GREEN){
                moneyAccount.setGreenDollar(moneyAccount.getGreenDollar() + amount);
            }else{
                throw new RuntimeException("Неизвестная валюта");
            }
        }else if(operationType.equals("remove")){
            if(money == Money.BLUE){
                if(moneyAccount.getBlueDollar() - amount >= 0) {
                    moneyAccount.setBlueDollar(moneyAccount.getBlueDollar() - amount);
                }else{
                    throw new RuntimeException("Недостаточно средств");
                }
            }else if(money == Money.RED){
                if(moneyAccount.getRedDollar() - amount >= 0) {
                    moneyAccount.setRedDollar(moneyAccount.getRedDollar() - amount);
                }else{
                    throw new RuntimeException("Недостаточно средств");
                }
            }else if(money == Money.GREEN){
                if(moneyAccount.getGreenDollar() - amount >= 0) {
                    moneyAccount.setGreenDollar(moneyAccount.getGreenDollar() - amount);
                }else{
                    throw new RuntimeException("Недостаточно средств");
                }
            }else{
                throw new RuntimeException("Неизвестная валюта");
            }
        }else{
            throw new RuntimeException("Неизвестная операция");
        }
        return moneyAccount;
    }
}
