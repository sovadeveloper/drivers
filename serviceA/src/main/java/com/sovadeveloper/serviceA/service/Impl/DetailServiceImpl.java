package com.sovadeveloper.serviceA.service.Impl;

import com.sovadeveloper.serviceA.dto.DetailDTO;
import com.sovadeveloper.serviceA.entity.Detail;
import com.sovadeveloper.serviceA.mapper.DetailMapper;
import com.sovadeveloper.serviceA.repository.CarRepo;
import com.sovadeveloper.serviceA.repository.DetailRepo;
import com.sovadeveloper.serviceA.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {

    private final DetailRepo detailRepo;
    private final CarRepo carRepo;
    private final DetailMapper detailMapper;

    @Override
    public DetailDTO setDetail(Detail detail) {
        validate(detail);
        Detail oldDetail = detailRepo.findByNameAndCar_Id(detail.getName(), detail.getCar().getId());
        if(oldDetail != null){
            throw new RuntimeException("Невозможно установить деталь, так как она уже установлена. Можно только заменить");
        }
        return detailMapper.toDto(detailRepo.save(detail));
    }

    @Transactional
    @Override
    public DetailDTO changeDetail(Detail newDetail) {
        validate(newDetail);
        Detail oldDetail = detailRepo.findByNameAndCar_Id(newDetail.getName(), newDetail.getCar().getId());
        if(oldDetail == null){
            throw new RuntimeException("Невозможно заменить несуществующую деталь");
        }
        detailRepo.deleteById(oldDetail.getId());
        return detailMapper.toDto(detailRepo.save(newDetail));
    }

    @Override
    public DetailDTO edit(Detail detail) {
        validate(detail);
        detailRepo.findById(detail.getId())
                .orElseThrow(() -> new RuntimeException("Данная деталь не найдена"));
        return detailMapper.toDto(detailRepo.save(detail));
    }

    @Override
    public DetailDTO getById(Long id) {
        return detailMapper.toDto(detailRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Данная деталь не найдена")));
    }

    @Override
    public DetailDTO getBySerialNumber(String serialNumber) {
        return detailMapper.toDto(detailRepo.findBySerialNumber(serialNumber));
    }

    @Override
    public Long delete(Long id) {
        detailRepo.deleteById(id);
        return id;
    }

    private void validate(Detail detail){
        if(detail.getName().isEmpty()){
            throw new RuntimeException("Название детали не может быть пустым");
        }
        if(detail.getSerialNumber().isEmpty()){
            throw new RuntimeException("Серийный номер не может быть пустым");
        }
        carRepo.findById(detail.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Данный автомобиль не найден"));
    }
}
