package org.acme.interfaces.rest;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.dto.list.CreateListDTO;
import org.acme.application.dto.list.UpdateListDTO;
import org.acme.application.usecase.list.*;
import org.acme.domain.exception.ListNotFoundException;

import java.util.UUID;

@Path("/list")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListResource {

    private final CreateListUseCase createListUseCase;
    private final GetUserListsUseCase getUserListsUseCase;
    private final GetListDetailUseCase getListDetailUseCase;
    private final UpdateListUseCase updateListUseCase;
    private final DeleteListUseCase deleteListUseCase;

    public ListResource(CreateListUseCase createListUseCase, GetUserListsUseCase getUserListsUseCase, GetListDetailUseCase getListDetailUseCase, UpdateListUseCase updateListUseCase, DeleteListUseCase deleteListUseCase) {
        this.createListUseCase = createListUseCase;
        this.getUserListsUseCase = getUserListsUseCase;
        this.getListDetailUseCase = getListDetailUseCase;
        this.updateListUseCase = updateListUseCase;
        this.deleteListUseCase = deleteListUseCase;
    }

    @POST
    public Response createList(@Valid CreateListDTO createListDTO) {
        return Response.status(Response.Status.CREATED).entity(createListUseCase.execute(createListDTO)).build();
    }

    @GET
    public Response getLists() {
        return Response.ok(getUserListsUseCase.execute()).build();
    }

    @GET
    @Path("/{id}")
    public Response getListDetail(@PathParam("id") UUID id) {
        try {
            return Response.ok(getListDetailUseCase.execute(id)).build();
        } catch (ListNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{id}")
    public Response updateList(@PathParam("id") UUID id, UpdateListDTO updateListDTO) {
        try {
            return Response.ok(updateListUseCase.execute(id, updateListDTO)).build();
        } catch (ListNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteList(@PathParam("id") UUID id) {
        try {
            deleteListUseCase.execute(id);
            return Response.noContent().build();
        } catch (ListNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
