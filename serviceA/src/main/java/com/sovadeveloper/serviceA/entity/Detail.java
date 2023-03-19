package com.sovadeveloper.serviceA.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
