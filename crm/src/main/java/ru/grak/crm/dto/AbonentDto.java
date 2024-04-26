package ru.grak.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grak.common.enums.TypeTariff;
import ru.grak.crm.entity.Client;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbonentDto {

    private String phoneNumber;
    private TypeTariff tariff;
    private BigDecimal balance = BigDecimal.valueOf(100);

    public Client toEntity() {
        return Client.builder()
                .phoneNumber(this.getPhoneNumber())
                .tariff(this.getTariff())
                .balance(this.getBalance())
                .build();
    }
}
