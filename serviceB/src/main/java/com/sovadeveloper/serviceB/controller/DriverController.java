package com.sovadeveloper.serviceB.controller;

import com.sovadeveloper.serviceB.entity.Driver;
import com.sovadeveloper.serviceB.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Driver driver){
        try {
            return ResponseEntity.ok(driverService.create(driver));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Driver driver){
        try {
            return ResponseEntity.ok(driverService.edit(driver));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(driverService.delete(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(driverService.getAll());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getAllPageable(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        try {
            return ResponseEntity.ok(driverService.getAllPageable(pageable));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(driverService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/passport")
    public ResponseEntity<?> getByPassport(@RequestParam String passport){
        try {
            return ResponseEntity.ok(driverService.getByPassport(passport));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
