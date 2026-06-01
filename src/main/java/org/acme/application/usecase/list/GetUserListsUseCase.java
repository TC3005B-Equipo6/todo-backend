package org.acme.application.usecase.list;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.application.dto.list.GetUserListsResponseDTO;
import org.acme.domain.repository.ListRepository;
import org.acme.infrastructure.security.AuthContext;

import java.util.List;

@ApplicationScoped
public class GetUserListsUseCase {

    private final ListRepository listRepository;
    private final AuthContext authContext;

    public GetUserListsUseCase(ListRepository listRepository, AuthContext authContext) {
        this.listRepository = listRepository;
        this.authContext = authContext;
    }

    public List<GetUserListsResponseDTO> execute(String q){
        return listRepository.getUserLists(authContext.getUser(), q);
    }
}
