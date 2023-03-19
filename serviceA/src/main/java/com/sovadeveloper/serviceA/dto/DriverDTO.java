package com.sovadeveloper.serviceA.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DriverDTO {
    private Long id;

    private String fullName;

    private String driverCategoryLicense;

    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;

    private String passport;

    private int experience;
}
