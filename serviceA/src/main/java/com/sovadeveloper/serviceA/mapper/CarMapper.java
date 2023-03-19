package com.sovadeveloper.serviceA.mapper;

import com.sovadeveloper.serviceA.dto.CarDTO;
import com.sovadeveloper.serviceA.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CarMapper extends EntityMapper<CarDTO, Car>{
}
