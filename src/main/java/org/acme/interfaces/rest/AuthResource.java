package org.acme.interfaces.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.usecase.user.ValidateAuthUseCase;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final ValidateAuthUseCase validateAuthUseCase;

    public AuthResource(ValidateAuthUseCase validateAuthUseCase){
        this.validateAuthUseCase = validateAuthUseCase;
    }

    @GET
    public Response validateToken(){
        return Response.ok(validateAuthUseCase.execute()).build();
    }
}
