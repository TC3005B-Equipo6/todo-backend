package org.acme.domain.repository;

import jakarta.ws.rs.core.Response;

public interface AuthRepository {
    Response validateToken(String authHeader);
}
