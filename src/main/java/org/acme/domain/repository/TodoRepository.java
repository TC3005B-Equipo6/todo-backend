package org.acme.domain.repository;

import org.acme.application.dto.todo.UpdateTodoDTO;
import org.acme.domain.model.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoRepository {
    Todo save(Todo todo);
    List<Todo> findAllTodos();
    void deleteTodoById(UUID id);
    Todo updateTodo(UUID id, UpdateTodoDTO updateTodoDTO);
    List<Todo> list();
    Todo findTodoById(UUID id);
}