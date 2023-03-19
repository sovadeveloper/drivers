package com.sovadeveloper.serviceA.service;

import com.sovadeveloper.serviceA.dto.DetailDTO;
import com.sovadeveloper.serviceA.entity.Detail;

public interface DetailService {
    DetailDTO setDetail(Detail detail);
    DetailDTO changeDetail(Detail newDetail);
    DetailDTO edit(Detail detail);
    DetailDTO getById(Long id);
    DetailDTO getBySerialNumber(String serialNumber);
    Long delete(Long id);
}
