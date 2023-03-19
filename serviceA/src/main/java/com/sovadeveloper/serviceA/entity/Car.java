package com.sovadeveloper.serviceA.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin", nullable = false, unique = true)
    private String VIN;

    @Column(name = "state_number", nullable = false, unique = true)
    private String stateNumber;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "brand")
    private String brand;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @Column(name = "owner_passport")
    private String ownerPassport;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private List<Detail> details;
}
