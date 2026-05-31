package org.acme.application.usecase.todo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.dto.todo.CreateTodoDTO;
import org.acme.domain.model.Todo;
import org.acme.domain.repository.TodoRepository;
import org.acme.infrastructure.security.AuthContext;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class CreateTodoUseCase {

    private final TodoRepository todoRepository;
    private final AuthContext authContext;

    @Inject
    public CreateTodoUseCase(TodoRepository todoRepository, AuthContext authContext) {
        this.todoRepository = todoRepository;
        this.authContext = authContext;
    }

    public Todo execute(CreateTodoDTO todoDTO) {
        Todo todo = Todo.builder()
                .id(UUID.randomUUID())
                .title(todoDTO.title())
                .description(todoDTO.description())
                .completed(false)
                .dueDate(todoDTO.dueDate())
                .createdAt(LocalDateTime.now())
                .listId(todoDTO.listId())
                .ownerId(authContext.getUser().getId())
                .build();

        return todoRepository.save(todo);
    }
}
