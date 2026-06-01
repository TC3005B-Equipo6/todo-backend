package org.acme.interfaces.rest;

import io.quarkus.security.UnauthorizedException;
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
        try {
            validateAuthUseCase.execute();
            return Response.noContent().build();
        } catch (UnauthorizedException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("/user")
    public Response getUserProfile(){
        try {
            return Response.ok().entity(validateAuthUseCase.execute()).build();
        } catch (UnauthorizedException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
