package org.acme.domain.repository;

import org.acme.application.dto.list.GetListDetailResponseDTO;
import org.acme.application.dto.list.GetUserListsResponseDTO;
import org.acme.application.dto.list.UpdateListDTO;
import org.acme.domain.model.TodoList;
import org.acme.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface ListRepository {
    TodoList createList(TodoList todoList);
    List<GetUserListsResponseDTO> getUserLists(User user, String q);
    GetListDetailResponseDTO getListDetail(UUID id, String q);
    TodoList updateList(UUID id, UpdateListDTO updateListDTO);
    void deleteList(UUID id);
}
