package org.acme.application.usecase.list;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.application.dto.list.UpdateListDTO;
import org.acme.domain.model.TodoList;
import org.acme.domain.repository.ListRepository;

import java.util.UUID;

@ApplicationScoped
public class UpdateListUseCase {

    private final ListRepository listRepository;

    public UpdateListUseCase(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public TodoList execute(UUID id, UpdateListDTO updateListDTO){
        return listRepository.updateList(id, updateListDTO);
    }
}
