package com.sovadeveloper.serviceA.controller;

import com.sovadeveloper.serviceA.entity.Car;
import com.sovadeveloper.serviceA.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@Tag(name="Автомобили", description="Контроллер отвечающий за работу с сущностями Car")
public class CarController {
    private final CarService carService;

    @Operation(
            summary = "Создание автомобиля",
            description = "Позволяет создать новый объект Car"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Parameter(description = "Объект Car") Car car){
        try {
            return ResponseEntity.ok(carService.create(car));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Установка владельца",
            description = "Позволяет установить владельца по его паспорту"
    )
    @PatchMapping ("/setOwner/{VIN}")
    public void setOwner(@PathVariable @Parameter(description = "VIN номер") String VIN,
                         @RequestBody @Parameter(description = "Паспорт") String passport){
        try {
            carService.setOwner(VIN, passport);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Operation(
            summary = "Снятие владельца",
            description = "Позволяет убрать владельца"
    )
    @PatchMapping ("/removeOwner/{VIN}")
    public void removeOwner(@PathVariable @Parameter(description = "VIN номер") String VIN){
        try {
            carService.removeOwner(VIN);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Operation(
            summary = "Редактирование автомобиля",
            description = "Позволяет отредактировать объект Car"
    )
    @PutMapping
    public ResponseEntity<?> edit(@RequestBody @Parameter(description = "Объект Car") Car car){
        try {
            return ResponseEntity.ok(carService.edit(car));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Удаление автомобиля",
            description = "Позволяет удалить объект Car"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Parameter(description = "Идентификатор Car") Long id){
        try {
            return ResponseEntity.ok(carService.delete(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение списка всех объектов Car (без пагинации)",
            description = "Позволяет получить все имеющиеся объекты Car"
    )
    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(carService.getAll());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение списка всех объектов Car (с пагинацией)",
            description = "Позволяет получить все имеющиеся объекты Car постранично"
    )
    @GetMapping("/page")
    public ResponseEntity<?> getAllPageable(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        try {
            return ResponseEntity.ok(carService.getAllPageable(pageable));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение объекта Car по его ID",
            description = "Позволяет получить объект Car по его ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable @Parameter(description = "Идентификатор Car") Long id){
        try {
            return ResponseEntity.ok(carService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение объекта Car по его VIN",
            description = "Позволяет получить объект Car по его VIN номеру"
    )
    @GetMapping("/VIN")
    public ResponseEntity<?> getByVIN(@RequestParam @Parameter(description = "VIN номер") String VIN){
        try {
            return ResponseEntity.ok(carService.getByVIN(VIN));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
