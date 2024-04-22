package ru.grak.brt.entity;

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
@Table(name = "call_cost")
public class CallCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private TypeTariff tariff_id;
    private BigDecimal internalCost;
    private BigDecimal externalCost;
}
