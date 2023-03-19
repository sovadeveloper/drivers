package com.sovadeveloper.serviceB.repository;

import com.sovadeveloper.serviceB.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver, Long> {
    Driver findByPassport(String passport);

    @Query(value = "SELECT * FROM drivers WHERE extract(day from birthday) = extract(day from current_date) " +
            "AND extract(month from birthday) = extract(month from current_date)", nativeQuery = true)
    List<Driver> findAllWithBirthdayToday();
}
