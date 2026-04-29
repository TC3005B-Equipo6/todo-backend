package org.acme.application.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.models.Todo;
import org.acme.domain.repository.TodoRepository;

import java.util.List;

@ApplicationScoped
public class ListUseCase {
    private final TodoRepository todoRepository;

    @Inject
    public ListUseCase(TodoRepository todoRepository){ this.todoRepository = todoRepository; }

    public List<Todo> execute(){
        return todoRepository.list();
    }
}