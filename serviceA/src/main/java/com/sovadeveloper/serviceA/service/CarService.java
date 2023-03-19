package com.sovadeveloper.serviceA.service;

import com.sovadeveloper.serviceA.dto.CarDTO;
import com.sovadeveloper.serviceA.dto.DriverDTO;
import com.sovadeveloper.serviceA.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarDTO create(Car car);
    CarDTO edit(Car car);
    CarDTO getById(Long id);
    CarDTO getByVIN(String VIN);
    List<CarDTO> getAll();
    Page<CarDTO> getAllPageable(Pageable pageable);
    void setOwner(String VIN, String passport);
    void removeOwner(String VIN);
    Long delete(Long id);
}
