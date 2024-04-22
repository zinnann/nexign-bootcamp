package ru.grak.brt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "traffic")
public class Traffic {
    @Id
    private String msisdn;
    private Integer inCallMinutesPerMonth;
    private Integer outCallMinutesPerMonth;
    private Integer month;
}
