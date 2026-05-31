package org.acme.application.dto.list;

import org.acme.application.dto.todo.GetTodoResponseDTO;

import java.util.List;
import java.util.UUID;

public record GetListDetailResponseDTO(
        UUID id,
        List<GetTodoResponseDTO> todos
) {
}
