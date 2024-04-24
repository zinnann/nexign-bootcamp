package ru.grak.hrs.service.tariff;

import org.springframework.stereotype.Component;

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

        //create own ex
        throw new RuntimeException("");
    }
}
