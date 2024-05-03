package ru.grak.crm.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    @Pattern(regexp = "^\\d{11}$")
    private String msisdn;

    @Positive
    private BigDecimal money;
}
