package ru.grak.crm.exceptions;

public class UnchangedTariffException extends RuntimeException {

    public UnchangedTariffException(String message) {
        super(message);
    }
}
