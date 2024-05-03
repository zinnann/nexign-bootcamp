package ru.grak.crm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeTariffDto {

    @Pattern(regexp = "^\\d{11}$")
    private String msisdn;

    @NotBlank
    private String tariffId;
}
