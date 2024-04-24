package ru.grak.hrs.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Long callCostType;
    private BigDecimal internalCost;
    private BigDecimal externalCost;
}
