package org.acme.application.usecase.list;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.application.dto.list.GetListDetailResponseDTO;
import org.acme.domain.repository.ListRepository;

import java.util.UUID;

@ApplicationScoped
public class GetListDetailUseCase {

    private final ListRepository listRepository;

    public GetListDetailUseCase(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public GetListDetailResponseDTO execute(UUID id, String q){
        return listRepository.getListDetail(id, q);
    }
}
