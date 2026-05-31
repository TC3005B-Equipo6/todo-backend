package org.acme.application.usecase.todo;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.application.dto.todo.UpdateTodoDTO;
import org.acme.domain.model.Todo;
import org.acme.domain.repository.TodoRepository;

import java.util.UUID;

@ApplicationScoped
public class UpdateTodoUseCase {

    private final TodoRepository todoRepository;

    public UpdateTodoUseCase(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo execute(UUID id, UpdateTodoDTO updateTodoDTO){
        return todoRepository.updateTodo(id, updateTodoDTO);
    }
}
