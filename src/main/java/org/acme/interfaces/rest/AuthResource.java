package org.acme.interfaces.rest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.usecase.LoginUseCase;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    LoginUseCase loginUseCase;

    public AuthResource(LoginUseCase loginUseCase){
        this.loginUseCase = loginUseCase;
    }

    @GET
    public Response validateToken(@HeaderParam("Authorization") String authHeader){
        return loginUseCase.execute(authHeader);
    }
}
