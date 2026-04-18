package org.acme.interfaces.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.dto.CreateTodoDto;
import org.acme.application.usecase.CreateTodoUseCase;
import org.acme.application.usecase.DeleteTodoUseCase;
import org.acme.application.usecase.ListUseCase;
import org.acme.domain.models.Todo;

import java.util.List;
import java.util.UUID;

@Path("/todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final CreateTodoUseCase createTodoUseCase;
    private final DeleteTodoUseCase deleteTodoUseCase;
    private final ListUseCase listUseCase;

    @Inject
    public TodoResource(CreateTodoUseCase createTodoUseCase,
                        DeleteTodoUseCase deleteTodoUseCase,
                        ListUseCase listUseCase) {
        this.createTodoUseCase = createTodoUseCase;
        this.deleteTodoUseCase = deleteTodoUseCase;
        this.listUseCase = listUseCase;
    }

    @POST
    public Response createTodo(CreateTodoDto todoDto) {
        Todo todo= createTodoUseCase.execute(todoDto);
        return Response.ok(todo).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") UUID id) {
        boolean deleted = deleteTodoUseCase.execute(id);
        return deleted
                ? Response.noContent().build()
                : Response.status(404).build();
    }

    @GET
    public Response list(){
        List<Todo> todos = listUseCase.execute();
        return Response.ok(todos).build();
    }
}
