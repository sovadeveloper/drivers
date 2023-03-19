package com.sovadeveloper.serviceB.mapper;

import com.sovadeveloper.serviceB.dto.MoneyAccountDTO;
import com.sovadeveloper.serviceB.entity.MoneyAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MoneyAccountMapper extends EntityMapper<MoneyAccountDTO, MoneyAccount>{
}
