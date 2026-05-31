package org.acme.application.dto.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTodoDTO(
        @NotBlank(message = "El título es obligatorio")
        String title,
        @NotBlank(message = "La descripción es obligatoria")
        String description,
        @NotNull(message = "La fecha de vencimiento es obligatoria")
        LocalDateTime dueDate,
        @NotBlank(message = "La lista es obligatoria")
        UUID listId
) {
}
