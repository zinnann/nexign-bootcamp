package ru.grak.crm.exceptions;

import java.text.MessageFormat;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
