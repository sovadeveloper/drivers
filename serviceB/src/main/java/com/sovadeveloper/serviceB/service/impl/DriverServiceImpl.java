package com.sovadeveloper.serviceB.service.impl;

import com.sovadeveloper.serviceB.dto.DriverDTO;
import com.sovadeveloper.serviceB.entity.Driver;
import com.sovadeveloper.serviceB.entity.MoneyAccount;
import com.sovadeveloper.serviceB.mapper.DriverMapper;
import com.sovadeveloper.serviceB.repository.DriverRepo;
import com.sovadeveloper.serviceB.repository.MoneyAccountRepo;
import com.sovadeveloper.serviceB.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{

    private final DriverRepo driverRepo;
    private final MoneyAccountRepo moneyAccountRepo;
    private final DriverMapper driverMapper;
    @Value("${salt.value}")
    private String salt;

    @Override
    @Transactional
    public DriverDTO create(Driver driver) {
        validate(driver);
        driver.setPassport(BCrypt.hashpw(driver.getPassport(), salt));
        DriverDTO driverDTO = driverMapper.toDto(driverRepo.save(driver));
        moneyAccountRepo.save(createMoneyAccount(driver));
        return driverDTO;
    }

    @Override
    public DriverDTO edit(Driver driver) {
        validate(driver);
        driver.setPassport(BCrypt.hashpw(driver.getPassport(), salt));
        driverRepo.findById(driver.getId())
                .orElseThrow(() -> new RuntimeException("Данный водитель не найден"));
        return driverMapper.toDto(driverRepo.save(driver));
    }

    @Override
    public DriverDTO getById(Long id) {
        return driverMapper.toDto(driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Данный водитель не найден")));
    }

    @Override
    public DriverDTO getByPassport(String passport) {
        return driverMapper.toDto(driverRepo.findByPassport(BCrypt.hashpw(passport, salt)));
    }

    @Override
    public List<DriverDTO> getAll() {
        return driverMapper.toDto(driverRepo.findAll());
    }

    @Override
    public Page<DriverDTO> getAllPageable(Pageable pageable) {
        Page<Driver> drivers = driverRepo.findAll(pageable);
        List<DriverDTO> driverDTOS = driverMapper.toDto(drivers.getContent());
        return new PageImpl<>(driverDTOS, pageable, drivers.getTotalElements());
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        moneyAccountRepo.deleteById(id);
        driverRepo.deleteById(id);
        return id;
    }

    private void validate(Driver driver){
        if(driver.getFullName().isEmpty()){
            throw new RuntimeException("ФИО не может быть пустым");
        }
        if(driver.getPassport().length() != 11){
            throw new RuntimeException("Паспорт введен некорректно, введите в формате: 'XXXX XXXXXX'");
        }
        if(driver.getDriverCategoryLicense().isEmpty()){
            throw new RuntimeException("Укажите категори(ю/и)");
        }
        if(driver.getBirthday().isAfter(LocalDate.now())){
            throw new RuntimeException("Укажите корректную дату дня рождения");
        }
        if(driver.getExperience() > driver.getBirthday().getYear() - 18){
            throw new RuntimeException("Укажите корректный стаж");
        }
    }

    private MoneyAccount createMoneyAccount(Driver driver){
        return MoneyAccount
                .builder()
                .redDollar(0)
                .greenDollar(0)
                .redDollar(0)
                .driver(driver)
                .build();
    }

    @Scheduled(cron = "@daily")
    public void happyBirthday(){
        List<Driver> drivers = driverRepo.findAllWithBirthdayToday();
        for(Driver driver: drivers){
            log.info("Поздравляем с днем рождения " + driver.getFullName() + "!");
        }
    }
}
