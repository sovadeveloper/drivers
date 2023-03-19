package com.sovadeveloper.serviceB.service;

import com.sovadeveloper.serviceB.dto.DriverDTO;
import com.sovadeveloper.serviceB.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriverService {
    DriverDTO create(Driver driver);
    DriverDTO edit(Driver driver);
    DriverDTO getById(Long id);
    DriverDTO getByPassport(String passport);
    List<DriverDTO> getAll();
    Page<DriverDTO> getAllPageable(Pageable pageable);
    Long delete(Long id);
}
