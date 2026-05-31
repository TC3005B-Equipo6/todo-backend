package org.acme.application.dto.list;

import org.acme.domain.model.TodoList;

import java.util.UUID;

public record GetUserListsResponseDTO(
        UUID id,
        String name
) {
    public static GetUserListsResponseDTO from(TodoList todoList) {
        return new GetUserListsResponseDTO(
                todoList.getId(),
                todoList.getName()
        );
    }
}
