package org.acme.infrastructure.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.acme.domain.repository.AuthRepository;

@ApplicationScoped
public class AuthRepositoryImpl implements AuthRepository {

    @Override
    @Transactional
    public Response validateToken(String authHeader){
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return Response.status(401).entity("No token").build();
            }

            String token = authHeader.substring(7).trim();

            FirebaseToken decodedToken =
                    FirebaseAuth.getInstance().verifyIdToken(token);

            return Response.ok("UID: " + decodedToken.getUid()).build();

        } catch (FirebaseAuthException e) {
            return Response.status(401)
                    .entity("Token inválido: " + e.getMessage())
                    .build();
        }
    }
}
