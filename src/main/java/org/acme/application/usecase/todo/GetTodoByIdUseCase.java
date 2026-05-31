package org.acme.application.usecase.todo;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.model.Todo;
import org.acme.domain.repository.TodoRepository;

import java.util.UUID;

@ApplicationScoped
public class GetTodoByIdUseCase {

    private final TodoRepository todoRepository;

    public GetTodoByIdUseCase(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo execute(UUID id) {
        return todoRepository.findTodoById(id);
    }
}
