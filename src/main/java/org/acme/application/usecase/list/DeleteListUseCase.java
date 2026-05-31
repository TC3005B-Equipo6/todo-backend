package org.acme.application.usecase.list;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.repository.ListRepository;

import java.util.UUID;

@ApplicationScoped
public class DeleteListUseCase {

    private final ListRepository listRepository;

    public DeleteListUseCase(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public void execute(UUID id){
        listRepository.deleteList(id);
    }
}
