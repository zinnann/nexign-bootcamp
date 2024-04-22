package ru.grak.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grak.common.enums.TypeCall;
import ru.grak.common.enums.TypeTariff;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallDataRecordPlusDto {

    private TypeCall typeCall;
    private String msisdnFirst;
    private String msisdnSecond;
    private Long dateTimeStartCall;
    private Long dateTimeEndCall;
    private TypeTariff typeTariff;
}
