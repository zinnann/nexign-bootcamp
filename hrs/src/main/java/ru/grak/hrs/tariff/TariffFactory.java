package ru.grak.hrs.tariff;

import org.springframework.stereotype.Component;
import ru.grak.hrs.exception.TariffConversionException;

@Component
public final class TariffFactory {

    private TariffFactory() {
    }

    public static BaseTariff createTariff(String typeTariff) {

        if (typeTariff.equals("11")) {
            return new ClassicTariff();
        }

        if (typeTariff.equals("12")) {
            return new MonthlyTariff();
        }

        throw new TariffConversionException("Tariff type conversion error");
    }
}
