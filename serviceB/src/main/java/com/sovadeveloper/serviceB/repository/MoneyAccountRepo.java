package com.sovadeveloper.serviceB.repository;

import com.sovadeveloper.serviceB.entity.MoneyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyAccountRepo extends JpaRepository<MoneyAccount, Long> {

}
