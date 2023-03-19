package com.sovadeveloper.serviceA.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarDTO {
    private Long id;

    private String VIN;

    private String stateNumber;

    private String manufacturer;

    private String brand;

    private int yearOfRelease;

    private List<DetailDTO> details;
}
