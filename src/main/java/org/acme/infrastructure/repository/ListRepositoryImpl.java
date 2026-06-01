package org.acme.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.application.dto.list.GetListDetailResponseDTO;
import org.acme.application.dto.list.GetUserListsResponseDTO;
import org.acme.application.dto.list.UpdateListDTO;
import org.acme.application.dto.todo.GetTodoResponseDTO;
import org.acme.domain.exception.ListNotFoundException;
import org.acme.domain.exception.TodoNotFoundException;
import org.acme.domain.exception.UserNotFoundException;
import org.acme.domain.model.TodoList;
import org.acme.domain.model.User;
import org.acme.domain.repository.ListRepository;
import org.acme.infrastructure.entities.TodoListEntity;
import org.acme.infrastructure.entities.UserEntity;
import org.acme.infrastructure.mapper.ListMapper;
import org.acme.infrastructure.mapper.TodoMapper;
import org.acme.infrastructure.mapper.UserMapper;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListRepositoryImpl implements ListRepository, PanacheRepositoryBase<TodoListEntity, UUID> {

    @Override
    @Transactional
    public TodoList createList(TodoList todoList) {
        try {
            TodoListEntity todoListEntity = ListMapper.toEntity(todoList);

            UserEntity userReference = getEntityManager().getReference(UserEntity.class, todoList.getOwnerId());
            todoListEntity.setOwner(userReference);

            persist(todoListEntity);

            return ListMapper.toDomain(todoListEntity);
        } catch (ConstraintViolationException e) {
            throw new UserNotFoundException(todoList.getId());
        }
    }

    @Override
    public List<GetUserListsResponseDTO> getUserLists(User user) {
        List<TodoListEntity> entities = find("owner", UserMapper.toEntity(user))
                .list();
        return ListMapper.ToDomainList(entities).stream()
                .map(GetUserListsResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetListDetailResponseDTO getListDetail(UUID id) {
        TodoListEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new ListNotFoundException(id));

        List<GetTodoResponseDTO> todos = TodoMapper.toDomainList(entity.getTodos())
                .stream().map(GetTodoResponseDTO::from).toList();
        return new GetListDetailResponseDTO(id, entity.getName(), todos);
    }

    @Override
    @Transactional
    public TodoList updateList(UUID id, UpdateListDTO updateListDTO) {
        TodoListEntity todoListEntity = findByIdOptional(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        if (updateListDTO.name() != null)
            todoListEntity.setName(updateListDTO.name());

        return ListMapper.toDomain(todoListEntity);
    }

    @Override
    @Transactional
    public void deleteList(UUID id) {
        boolean deleted = deleteById(id);
        if (!deleted)
            throw new ListNotFoundException(id);
    }
}
