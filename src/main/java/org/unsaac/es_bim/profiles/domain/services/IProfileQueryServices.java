package org.unsaac.es_bim.profiles.domain.services;

import org.unsaac.es_bim.profiles.domain.model.aggregates.Profile;
import org.unsaac.es_bim.profiles.domain.model.queries.GetProfileByUserId;

import java.util.Optional;

public interface IProfileQueryServices {
    Optional<Profile> handle(GetProfileByUserId query) ;
}
