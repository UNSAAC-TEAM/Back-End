package org.unsaac.es_bim.iam.application.internal.outboundservices.services;

public interface TokenService {
    String generateToken(String username);
    String generateTokenWithId(String username,Long id);

    String getUsernameFromToken(String token);

    boolean validateToken(String token);
}
