package org.unsaac.es_bim.profiles.application.internal;

import org.springframework.stereotype.Service;
import org.unsaac.es_bim.profiles.domain.model.aggregates.Profile;
import org.unsaac.es_bim.profiles.domain.model.queries.GetProfileByUserId;
import org.unsaac.es_bim.profiles.domain.services.IProfileQueryServices;
import org.unsaac.es_bim.profiles.infrastructure.rest.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileQueryService implements IProfileQueryServices {
    private final ProfileRepository profileRepository;

    public ProfileQueryService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByUserId query) {
        return profileRepository.findByUserId(query.userId());
    }
}
