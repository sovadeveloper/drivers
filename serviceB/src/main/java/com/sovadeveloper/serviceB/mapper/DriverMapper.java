package com.sovadeveloper.serviceB.mapper;

import com.sovadeveloper.serviceB.dto.DriverDTO;
import com.sovadeveloper.serviceB.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DriverMapper extends EntityMapper<DriverDTO, Driver>{

}
