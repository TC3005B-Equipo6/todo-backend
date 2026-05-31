package org.acme.application.usecase.list;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.application.dto.list.CreateListDTO;
import org.acme.domain.model.TodoList;
import org.acme.domain.repository.ListRepository;
import org.acme.infrastructure.security.AuthContext;

import java.util.HashSet;
import java.util.UUID;

@ApplicationScoped
public class CreateListUseCase {

    private final ListRepository listRepository;
    private final AuthContext authContext;

    public CreateListUseCase(ListRepository listRepository, AuthContext authContext) {
        this.listRepository = listRepository;
        this.authContext = authContext;
    }

    public TodoList execute(CreateListDTO createListDTO){
        TodoList todoList = TodoList.builder()
                .id(UUID.randomUUID())
                .name(createListDTO.name())
                .ownerId(authContext.getUser().getId())
                .todoSet(new HashSet<>())
                .build();
        return listRepository.createList(todoList);
    }
}
