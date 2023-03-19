package com.sovadeveloper.serviceA.mapper;

import com.sovadeveloper.serviceA.dto.DetailDTO;
import com.sovadeveloper.serviceA.entity.Detail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DetailMapper extends EntityMapper<DetailDTO, Detail>{
}
