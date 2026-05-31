package org.acme.application.dto.list;

import jakarta.validation.constraints.NotNull;

public record UpdateListDTO(
        @NotNull(message = "El nombre es obligatorio")
        String name
) {
}
