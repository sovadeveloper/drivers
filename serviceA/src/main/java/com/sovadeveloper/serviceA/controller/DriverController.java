package com.sovadeveloper.serviceA.controller;

import com.sovadeveloper.serviceA.client.DriverClient;
import com.sovadeveloper.serviceA.dto.DriverDTO;
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
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
@Tag(name="Водители", description="Контроллер отвечающий за работу с сущностями Driver")
public class DriverController {
    private final DriverClient driverClient;

    @Operation(
            summary = "Создание водителя",
            description = "Позволяет создать новый объект Driver"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Parameter(description = "Объект Driver") DriverDTO driver){
        try {
            return ResponseEntity.ok(driverClient.create(driver));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Редактирование водителя",
            description = "Позволяет отредактировать объект Driver"
    )
    @PutMapping
    public ResponseEntity<?> edit(@RequestBody @Parameter(description = "Объект Driver") DriverDTO driver){
        try {
            return ResponseEntity.ok(driverClient.edit(driver));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Удаление водителя",
            description = "Позволяет удалить объект Driver"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Parameter(description = "Идентификатор Driver") Long id){
        try {
            return ResponseEntity.ok(driverClient.delete(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение списка всех объектов Driver (без пагинации)",
            description = "Позволяет получить все имеющиеся объекты Driver"
    )
    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(driverClient.getAll());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение списка всех объектов Driver (с пагинацией)",
            description = "Позволяет получить все имеющиеся объекты Driver постранично"
    )
    @GetMapping("/page")
    public ResponseEntity<?> getAllPageable(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        try {
            return ResponseEntity.ok(driverClient.getAllPageable(pageable));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение объекта Driver по его ID",
            description = "Позволяет получить объект Driver по его ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable @Parameter(description = "Идентификатор Driver") Long id){
        try {
            return ResponseEntity.ok(driverClient.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Получение объекта Driver по его паспорту",
            description = "Позволяет получить объект Driver по его паспорту"
    )
    @GetMapping("/passport")
    public ResponseEntity<?> getByPassport(@RequestParam @Parameter(description = "Паспорт") String passport){
        try {
            return ResponseEntity.ok(driverClient.getByPassport(passport));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
