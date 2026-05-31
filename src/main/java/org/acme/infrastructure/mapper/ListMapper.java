package org.acme.infrastructure.mapper;

import org.acme.domain.model.TodoList;
import org.acme.infrastructure.entities.TodoListEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {

    public static TodoList toDomain(TodoListEntity entity) {
        return TodoList.builder()
                .id(entity.getId())
                .name(entity.getName())
                .ownerId(entity.getOwner().getId())
                .build();
    }

    public static List<TodoList> ToDomainList(List<TodoListEntity> entities){
        return entities.stream()
                .map(ListMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static TodoListEntity toEntity(TodoList todoList){
        TodoListEntity entity = new TodoListEntity();
        entity.setId(todoList.getId());
        entity.setName(todoList.getName());
        return entity;
    }

}
