package org.unsaac.es_bim.profiles.application.internal;

import org.springframework.stereotype.Service;
import org.unsaac.es_bim.profiles.domain.model.commands.ChangeImageUrlByUserIdCommand;
import org.unsaac.es_bim.profiles.domain.model.commands.EditProfileByUserIdCommand;
import org.unsaac.es_bim.profiles.domain.services.IProfileCommandServices;
import org.unsaac.es_bim.profiles.infrastructure.rest.ProfileRepository;

@Service
public class ProfileCommandService implements IProfileCommandServices {
    private final ProfileRepository profileRepository;

    public ProfileCommandService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    @Override
    public Long handle(EditProfileByUserIdCommand command) {
        var profile=this.profileRepository.findByUserId(command.userId());
        if(profile.isEmpty()){
            throw new RuntimeException("Profile not found");
        }
        else{
            profile.get().editProfile( command.firstName(), command.lastName(), command.birthDay(),command.country(), command.city(), command.gender(), command.phoneNumber(), command.description());
            this.profileRepository.save(profile.get());
            return 1L;
        }
    }

    @Override
    public Long handle(ChangeImageUrlByUserIdCommand command) {
        var profile=this.profileRepository.findByUserId(command.userId());
        if(profile.isEmpty()){
            throw new RuntimeException("Profile not found");
        }
        else{
            profile.get().EditImageProfile(command.imageUrl());
            this.profileRepository.save(profile.get());
            return 1L;
        }
    }
}
