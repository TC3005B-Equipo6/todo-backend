package org.acme.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.application.dto.todo.UpdateTodoDTO;
import org.acme.domain.exception.ListNotFoundException;
import org.acme.domain.exception.TodoNotFoundException;
import org.acme.domain.model.Todo;
import org.acme.domain.repository.TodoRepository;
import org.acme.infrastructure.entities.TodoEntity;
import org.acme.infrastructure.entities.TodoListEntity;
import org.acme.infrastructure.entities.UserEntity;
import org.acme.infrastructure.mapper.TodoMapper;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TodoRepositoryImpl implements TodoRepository, PanacheRepositoryBase<TodoEntity, UUID> {

    @Override
    @Transactional
    public Todo save(Todo todo) {
        try {
            TodoEntity entity = TodoMapper.toEntity(todo);

            TodoListEntity listReference = getEntityManager().getReference(TodoListEntity.class, todo.getListId());
            entity.setList(listReference);

            UserEntity ownerReference = getEntityManager().getReference(UserEntity.class, todo.getOwnerId());
            entity.setOwner(ownerReference);

            persist (entity);
            return TodoMapper.toDomain(entity);
        } catch ( ConstraintViolationException e) {
            throw new ListNotFoundException(todo.getListId());
        }
    }

    @Override
    public List<Todo> findAllTodos() {
        List<TodoEntity> entities= findAll().stream().toList();
        List<Todo> response= new ArrayList<>();
        for (TodoEntity entity: entities) {
            response.add(TodoMapper.toDomain(entity));
        }
        return response;
    }

    @Override
    @Transactional
    public void deleteTodoById(UUID id){
        boolean deleted = deleteById(id);
        if (!deleted)
            throw new TodoNotFoundException(id);
    }

    @Override
    @Transactional
    public Todo updateTodo(UUID id, UpdateTodoDTO updateTodoDTO) {
        TodoEntity todoEntity = findByIdOptional(id)
                .orElseThrow(() -> new TodoNotFoundException(id));


        if (updateTodoDTO.title() != null)
            todoEntity.setTitle(updateTodoDTO.title());
        if (updateTodoDTO.description() != null)
            todoEntity.setDescription(updateTodoDTO.description());
        if (updateTodoDTO.completed() != null)
            todoEntity.setCompleted(updateTodoDTO.completed());
        if (updateTodoDTO.dueDate() != null)
            todoEntity.setDueDate(updateTodoDTO.dueDate());
        if (updateTodoDTO.listId() != null){
            TodoListEntity listReference = getEntityManager().getReference(TodoListEntity.class, updateTodoDTO.listId());
            todoEntity.setList(listReference);
        }
        return TodoMapper.toDomain(todoEntity);
    }

    @Override
    public List<Todo> list(){
        List<TodoEntity> entities = listAll();
        List<Todo> todos = new ArrayList<>();
        for (TodoEntity entity: entities){
            todos.add(TodoMapper.toDomain(entity));
        }
        return todos;
    }

    @Override
    public Todo findTodoById(UUID id) {
        TodoEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
        return TodoMapper.toDomain(entity);
    }
}