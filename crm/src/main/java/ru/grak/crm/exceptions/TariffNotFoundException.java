package ru.grak.crm.exceptions;

import java.text.MessageFormat;

public class TariffNotFoundException extends RuntimeException {

    public TariffNotFoundException(String message) {
        super(message);
    }

    public TariffNotFoundException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
