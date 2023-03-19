package com.sovadeveloper.serviceA.client;

import com.sovadeveloper.serviceA.dto.DriverDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "driverClient", url = "${client.service_b.url}/drivers")
public interface DriverClient {
    @PostMapping
    DriverDTO create(@RequestBody DriverDTO driver);

    @PutMapping
    DriverDTO edit(@RequestBody DriverDTO driver);

    @DeleteMapping("/{id}")
    Long delete(@PathVariable Long id);

    @GetMapping
    List<DriverDTO> getAll();

    @GetMapping("/page")
    Page<DriverDTO> getAllPageable(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable);

    @GetMapping("/{id}")
    DriverDTO getById(@PathVariable Long id);

    @GetMapping("/passport")
    DriverDTO getByPassport(@RequestParam String passport);
}
