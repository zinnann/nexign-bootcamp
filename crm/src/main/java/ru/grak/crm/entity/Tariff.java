package ru.grak.crm.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.grak.common.enums.TypeTariff;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tariff")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private TypeTariff tariff;

    private String name;
    private BigDecimal subscriptionFee;
    private Integer benefitMinutes;
    private Long inCallCostType;
    private Long outCallCostType;

}
