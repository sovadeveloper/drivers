package com.sovadeveloper.serviceA.repository;

import com.sovadeveloper.serviceA.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {
    Car findByVIN(String VIN);
}
