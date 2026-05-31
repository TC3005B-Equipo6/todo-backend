package org.acme.application.usecase.todo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.repository.TodoRepository;

import java.util.UUID;

@ApplicationScoped
public class DeleteTodoUseCase {

    private final TodoRepository todoRepository;

    @Inject
    public DeleteTodoUseCase(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public void execute(UUID id){
        todoRepository.deleteTodoById(id);
    }
}
