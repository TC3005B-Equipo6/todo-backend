package org.acme.application.dto.list;

import org.acme.application.dto.todo.GetTodoResponseDTO;

import java.util.List;
import java.util.UUID;

public record GetListDetailResponseDTO(
        UUID id,
        String name,
        List<GetTodoResponseDTO> todos
) {
}
