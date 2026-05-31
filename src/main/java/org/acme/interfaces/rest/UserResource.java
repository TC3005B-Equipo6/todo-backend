package org.acme.interfaces.rest;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.dto.todo.RegisterUserDTO;
import org.acme.application.security.PermitPublic;
import org.acme.application.usecase.user.RegisterUserUseCase;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final RegisterUserUseCase registerUserUseCase;

    public UserResource(RegisterUserUseCase registerUserUseCase){
        this.registerUserUseCase = registerUserUseCase;
    }

    @POST
    @PermitPublic
    public Response registerUser(@Valid RegisterUserDTO registerUserDto) {
        try {
            return Response.ok(registerUserUseCase.execute(registerUserDto)).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Path("/test")
    @GET
    public Response testEndpoint(){
        return Response.ok("Hello desde endpoint protegido").build();
    }
}