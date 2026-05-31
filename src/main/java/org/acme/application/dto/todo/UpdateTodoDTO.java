package org.acme.application.dto.todo;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateTodoDTO(
    @Size(message = "El nombre no puede ser vacío")
    String title,
    @Size(message = "La descripción no puede ser vacía")
    String description,
    Boolean completed,
    LocalDateTime dueDate,
    UUID listId
) {
}
