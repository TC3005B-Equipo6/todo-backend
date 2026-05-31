package org.acme.domain.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super(String.format("No se encontró Usuario con el id: %s", String.valueOf(id)));
    }
}
