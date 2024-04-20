package ru.grak.cdr.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.grak.cdr.enums.TypeCall;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cdr")
public class CallDataRecord {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeCall typeCall;
    private String msisdnFirst;
    private String msisdnSecond;
    private Long dateTimeStartCall;
    private Long dateTimeEndCall;
}
