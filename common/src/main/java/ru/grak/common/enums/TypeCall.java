package ru.grak.common.enums;

import ru.grak.common.exceptions.TypeNotFoundException;

public enum TypeCall {
    OUTGOING("01"),
    INCOMING("02");

    private final String numericValue;

    TypeCall(String numericValue) {
        this.numericValue = numericValue;
    }

    public String getNumericValueOfType() {
        return numericValue;
    }

    public static TypeCall fromNumericValueOfType(String numericValueOfType) {

        if (numericValueOfType.equals("01")) {
            return OUTGOING;
        }

        if (numericValueOfType.equals("02")) {
            return OUTGOING;
        }

        throw new TypeNotFoundException("Doesn't exist this type");
    }
}
