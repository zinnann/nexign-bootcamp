package ru.grak.common.enums;

import ru.grak.common.exceptions.TypeNotFoundException;

public enum TypeTariff {
    PER_MINUTE("11"),
    MONTHLY("12");

    private final String numericValue;

    TypeTariff(String numericValue) {
        this.numericValue = numericValue;
    }

    public String getNumericValueOfType() {
        return numericValue;
    }

    public static TypeTariff fromNumericValueOfType(String numericValueOfType) {

        if ("12".equals(numericValueOfType)) {
            return MONTHLY;
        }
        if ("11".equals(numericValueOfType)) {
            return PER_MINUTE;
        }

        throw new TypeNotFoundException("Doesn't exist this type");
    }
}
