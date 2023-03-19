package com.sovadeveloper.serviceA.controller;

import com.sovadeveloper.serviceA.entity.Detail;
import com.sovadeveloper.serviceA.service.DetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/details")
@RequiredArgsConstructor
@Tag(name="Детали", description="Контроллер отвечающий за работу с сущностями Detail")
public class DetailController {
    private final DetailService detailService;

    @Operation(
            summary = "Установка детали в автомобиле",
            description = "Позволяет установить НОВЫЙ объект Detail, в уже ИМЕЮЩИЙСЯ объект Car"
    )
    @PostMapping("/setDetail")
    public ResponseEntity<?> setDetail(@RequestBody @Parameter(description = "Объект Detail") Detail detail){
        try {
            return ResponseEntity.ok(detailService.setDetail(detail));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Замена детали в автомобиле",
            description = "Позволяет заменить старый объект Detail, на новый"
    )
    @PostMapping("/changeDetail")
    public ResponseEntity<?> changeDetail(@RequestBody @Parameter(description = "Объект Detail") Detail detail){
        try {
            return ResponseEntity.ok(detailService.changeDetail(detail));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Редактирование детали",
            description = "Позволяет отредактировать объект Detail"
    )
    @PutMapping
    public ResponseEntity<?> edit(@RequestBody @Parameter(description = "Объект Detail") Detail detail){
        try {
            return ResponseEntity.ok(detailService.edit(detail));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Удаление детали",
            description = "Позволяет удалить объект Detail"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Parameter(description = "Идентификатор Detail") Long id){
        try {
            return ResponseEntity.ok(detailService.delete(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение объекта Detail по его ID",
            description = "Позволяет получить объект Detail по его ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable @Parameter(description = "Идентификатор Detail") Long id){
        try {
            return ResponseEntity.ok(detailService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение объекта Detail по его серийному номеру",
            description = "Позволяет получить объект Detail по его серийному номеру"
    )
    @GetMapping("/serialNumber")
    public ResponseEntity<?> getBySerialNumber(
            @RequestParam @Parameter(description = "Серийный номер детали") String serialNumber){
        try {
            return ResponseEntity.ok(detailService.getBySerialNumber(serialNumber));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
