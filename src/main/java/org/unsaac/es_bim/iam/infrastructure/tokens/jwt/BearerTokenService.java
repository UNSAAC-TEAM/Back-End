package org.unsaac.es_bim.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.unsaac.es_bim.iam.application.internal.outboundservices.services.TokenService;

public interface BearerTokenService extends TokenService {
    String getBearerTokenFrom(HttpServletRequest Request);
    String generateToken(Authentication authentication);
}
