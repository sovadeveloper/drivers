package com.sovadeveloper.serviceB.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "money_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoneyAccount {
    @Id
    private Long id;

    @Column(name = "red_dollar")
    private double redDollar;

    @Column(name = "green_dollar")
    private double greenDollar;

    @Column(name = "blue_dollar")
    private double blueDollar;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Driver driver;
}
