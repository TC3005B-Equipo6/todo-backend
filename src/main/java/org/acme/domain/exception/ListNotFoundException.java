package org.acme.domain.exception;

import java.util.UUID;

public class ListNotFoundException extends RuntimeException {
    public ListNotFoundException(UUID id) {
        super(String.format("No se encontró Lista con el id: %s", String.valueOf(id)));
    }
}
