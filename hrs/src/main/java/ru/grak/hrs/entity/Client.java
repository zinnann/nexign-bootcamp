package ru.grak.hrs.entity;

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
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private TypeTariff tariff;
    private BigDecimal balance;
}
