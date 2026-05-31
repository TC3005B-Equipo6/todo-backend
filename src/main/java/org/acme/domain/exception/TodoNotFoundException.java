package org.acme.domain.exception;

import java.util.UUID;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(UUID id) {
        super(String.format("No se encontró Todo con el id: %s", String.valueOf(id)));
    }
}
