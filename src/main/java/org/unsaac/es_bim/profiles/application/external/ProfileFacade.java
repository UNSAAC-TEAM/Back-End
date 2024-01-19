package org.unsaac.es_bim.profiles.application.external;

import org.springframework.stereotype.Service;
import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;
import org.unsaac.es_bim.profiles.domain.model.aggregates.Profile;
import org.unsaac.es_bim.profiles.infrastructure.rest.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileFacade {
    private final ProfileRepository profileRepository;

    public ProfileFacade(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    public Optional<Profile> getProfileByUserId(Long id){
        return profileRepository.findByUserId(id);
    }

    public String getProfileNameByUserId(Long id){
        var profile=this.profileRepository.findByUserId(id);
        if(profile.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return profile.get().getFirstName()+" "+profile.get().getLastName();
    }
}
