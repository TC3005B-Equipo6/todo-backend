package org.acme.application.dto.todo;

import org.acme.domain.model.Todo;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetTodoResponseDTO(
        UUID id,
        String title,
        boolean completed,
        LocalDateTime dueDate
) {
    public static GetTodoResponseDTO from(Todo todo){
        return new GetTodoResponseDTO(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted(),
                todo.getDueDate()
        );
    }
}
