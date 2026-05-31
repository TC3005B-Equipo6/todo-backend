package org.acme.interfaces.rest;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.dto.todo.CreateTodoDTO;
import org.acme.application.dto.todo.UpdateTodoDTO;
import org.acme.application.usecase.todo.*;
import org.acme.domain.exception.TodoNotFoundException;

import java.util.UUID;

@Path("/todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final CreateTodoUseCase createTodoUseCase;
    private final DeleteTodoUseCase deleteTodoUseCase;
    private final UpdateTodoUseCase updateTodoUseCase;
    private final ListUseCase listUseCase;
    private final GetTodoByIdUseCase getTodoByIdUseCase;

    public TodoResource(CreateTodoUseCase createTodoUseCase,
                        DeleteTodoUseCase deleteTodoUseCase,
                        ListUseCase listUseCase,
                        UpdateTodoUseCase updateTodoUseCase,
                        GetTodoByIdUseCase getTodoByIdUseCase) {
        this.createTodoUseCase = createTodoUseCase;
        this.deleteTodoUseCase = deleteTodoUseCase;
        this.updateTodoUseCase = updateTodoUseCase;
        this.listUseCase = listUseCase;
        this.getTodoByIdUseCase = getTodoByIdUseCase;
    }

    @POST
    public Response createTodo(CreateTodoDTO todoDto) {
        return Response.status(Response.Status.CREATED).entity(createTodoUseCase.execute(todoDto)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response updateTodo(@PathParam("id") UUID id, UpdateTodoDTO updateTodoDTO){
        try {
            return Response.ok(updateTodoUseCase.execute(id, updateTodoDTO)).build();
        } catch (TodoNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        try {
            deleteTodoUseCase.execute(id);
            return Response.noContent().build();
        } catch ( TodoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response list(){
        return Response.ok(listUseCase.execute()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        try {
            return Response.ok(getTodoByIdUseCase.execute(id)).build();
        } catch (TodoNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
