package ru.grak.hrs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallDetailsDto {

    private String msisdn;
    private Duration duration;
    private BigDecimal cost;
}
