package ru.grak.crm.exceptions;

import java.text.MessageFormat;

public class AbonentAlreadyExistException extends RuntimeException {

    public AbonentAlreadyExistException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
