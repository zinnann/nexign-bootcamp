package ru.grak.common.enums;

import lombok.EqualsAndHashCode;

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

        if (numericValueOfType.equals("11")) {
            return PER_MINUTE;
        }
        return MONTHLY;
    }
}