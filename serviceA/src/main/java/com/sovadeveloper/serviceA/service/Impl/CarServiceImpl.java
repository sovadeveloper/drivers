package com.sovadeveloper.serviceA.service.Impl;

import com.sovadeveloper.serviceA.client.DriverClient;
import com.sovadeveloper.serviceA.dto.CarDTO;
import com.sovadeveloper.serviceA.dto.DriverDTO;
import com.sovadeveloper.serviceA.entity.Car;
import com.sovadeveloper.serviceA.mapper.CarMapper;
import com.sovadeveloper.serviceA.repository.CarRepo;
import com.sovadeveloper.serviceA.service.CarService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;
    private final CarMapper carMapper;
    private final DriverClient driverClient;
    @Value("${salt.value}")
    private String salt;

    @Override
    public CarDTO create(Car car) {
        validate(car);
        return carMapper.toDto(carRepo.save(car));
    }

    @Override
    public CarDTO edit(Car car) {
        validate(car);
        carRepo.findById(car.getId())
                .orElseThrow(() -> new RuntimeException("Данный автомобиль не найден"));
        return carMapper.toDto(carRepo.save(car));
    }

    @Override
    public CarDTO getById(Long id) {
        return carMapper.toDto(carRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Данный автомобиль не найден")));
    }

    @Override
    public CarDTO getByVIN(String VIN) {
        return carMapper.toDto(carRepo.findByVIN(VIN));
    }

    @Override
    public List<CarDTO> getAll() {
        return carMapper.toDto(carRepo.findAll());
    }

    @Override
    public Page<CarDTO> getAllPageable(Pageable pageable) {
        Page<Car> cars = carRepo.findAll(pageable);
        List<CarDTO> carDTOS = carMapper.toDto(cars.getContent());
        return new PageImpl<>(carDTOS, pageable, cars.getTotalElements());
    }

    @Override
    public void setOwner(String VIN, String passport) {
        Car car = carRepo.findByVIN(VIN);
        if(car == null){
            throw new RuntimeException("Данный автомобиль не найден");
        }
        if(driverClient.getByPassport(passport) == null){
            throw new RuntimeException("Данный водитель не найден");
        }
        car.setOwnerPassport(BCrypt.hashpw(passport, salt));
        carRepo.save(car);
    }

    @Override
    public void removeOwner(String VIN) {
        Car car = carRepo.findByVIN(VIN);
        if(car == null){
            throw new RuntimeException("Данный автомобиль не найден");
        }
        car.setOwnerPassport(null);
        carRepo.save(car);
    }

    @Override
    public Long delete(Long id) {
        carRepo.deleteById(id);
        return id;
    }

    public void validate(Car car){
        System.out.println(car.getVIN());
        if(car.getVIN().isEmpty()){
            throw new RuntimeException("VIN не может быть пустым");
        }
        if(car.getStateNumber().isEmpty()){
            throw new RuntimeException("Гос. номер не может быть пустым");
        }
        if(car.getYearOfRelease() > LocalDate.now().getYear()){
            throw new RuntimeException("Укажите корректный год производства. " +
                    "Автомобиль не может быть произведен позже текущего года");
        }
    }
}
