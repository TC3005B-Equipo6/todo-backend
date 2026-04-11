package org.acme.interfaces.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.dto.RegisterUserDto;
import org.acme.application.usecase.DeleteTodoUseCase;
import org.acme.application.usecase.RegisterUserUseCase;
import org.acme.infrastructure.security.AuthContext;

import java.util.UUID;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    RegisterUserUseCase registerUserUseCase;

    @Inject
    AuthContext authContext;
    @Inject
    DeleteTodoUseCase deleteTodoUseCase;

    public UserResource(RegisterUserUseCase registerUserUseCase, AuthContext authContext){
        this.registerUserUseCase = registerUserUseCase;
        this.authContext = authContext;
    }

    @POST
    public Response registerUser(@Valid RegisterUserDto registerUserDto) {
        try {
            return Response.ok(registerUserUseCase.execute(registerUserDto)).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        // 🐛 BUG INTENCIONAL: ignoramos el id recibido
        //    y siempre borramos el ToDo con id = 1
        boolean deleted = deleteTodoUseCase.execute(UUID.fromString("1"));
        return deleted
                ? Response.noContent().build()
                : Response.status(404).build();
    }

    @Path("/test")
    @GET
    public Response testEndpoint(){
        System.out.println("En el endpoint: "+authContext.getUser().getId());
        return Response.ok("Hello").build();
    }
}