package com.sovadeveloper.serviceB.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "passport", nullable = false, unique = true)
    private String passport;

    @Column(name = "driver_category_license", nullable = false)
    private String driverCategoryLicense;

    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "experience", nullable = false)
    private int experience;
}
