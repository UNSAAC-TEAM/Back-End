package org.unsaac.es_bim.profiles.infrastructure.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unsaac.es_bim.profiles.domain.model.aggregates.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

    Optional<Profile> findByUserId(Long userId);
}
