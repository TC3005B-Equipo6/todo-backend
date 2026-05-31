package org.acme.infrastructure.mapper;

import org.acme.domain.model.Todo;
import org.acme.infrastructure.entities.TodoEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TodoMapper {

    public static Todo toDomain(TodoEntity entity) {
        return Todo.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .completed(entity.isCompleted())
                .dueDate(entity.getDueDate())
                .listId(entity.getList().getId())
                .createdAt(entity.getCreatedAt())
                .ownerId(entity.getOwner().getId())
                .build();
    }

    public static List<Todo> toDomainList(Set<TodoEntity> entities){
        return entities.stream()
                .map(TodoMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static TodoEntity toEntity(Todo todo) {
        TodoEntity entity = new TodoEntity();
        entity.setId(todo.getId());
        entity.setTitle(todo.getTitle());
        entity.setDescription(todo.getDescription());
        entity.setCompleted(todo.isCompleted());
        entity.setDueDate(todo.getDueDate());
        entity.setCreatedAt(todo.getCreatedAt());
        return entity;
    }

}