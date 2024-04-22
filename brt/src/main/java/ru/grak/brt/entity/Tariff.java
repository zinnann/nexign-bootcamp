package ru.grak.brt.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    private TypeTariff tariff_id;

    private String name;
    private BigDecimal subscriptionFee;
    private Integer benefitMinutes;
    private Long inCallCostType;
    private Long outCallCostType;

}
