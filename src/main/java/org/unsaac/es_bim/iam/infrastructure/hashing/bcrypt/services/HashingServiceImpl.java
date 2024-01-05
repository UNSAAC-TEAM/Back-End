package org.unsaac.es_bim.iam.infrastructure.hashing.bcrypt.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.infrastructure.hashing.bcrypt.BCryptHashingService;

@Service
public class HashingServiceImpl implements BCryptHashingService {

    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructor.
     */
    public HashingServiceImpl() {
        passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
