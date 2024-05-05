package ru.grak.cdr.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeCall;
    private String msisdnFirst;
    private String msisdnSecond;
    private Long dateTimeStartCall;
    private Long dateTimeEndCall;
}
