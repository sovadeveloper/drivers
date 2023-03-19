package com.sovadeveloper.serviceA.repository;

import com.sovadeveloper.serviceA.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepo extends JpaRepository<Detail, Long> {
    Detail findBySerialNumber(String serialNumber);
    Detail findByNameAndCar_Id(String name, Long id);
}
