package org.unsaac.es_bim.profiles.application.internal;

import org.springframework.stereotype.Service;
import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;
import org.unsaac.es_bim.blog.domain.model.commands.CreateBlogCommand;
import org.unsaac.es_bim.profiles.domain.model.commands.ChangeImageUrlByUserIdCommand;
import org.unsaac.es_bim.profiles.domain.model.commands.EditProfileByUserIdCommand;
import org.unsaac.es_bim.profiles.domain.services.IProfileCommandServices;
import org.unsaac.es_bim.profiles.infrastructure.rest.ProfileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class ProfileCommandService implements IProfileCommandServices {
    private final ProfileRepository profileRepository;
    private static final Logger logger = LogManager.getLogger(ProfileCommandService.class);
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

    @Override
    public Long handle(CreateBlogCommand command) {
        var profile=this.profileRepository.findByUserId(command.userId());
        if(profile.isEmpty()){
            throw new RuntimeException("Profile not found");
        }
        var blog=new Blog(command.title(), command.label(), command.imageUrl(), command.description(), command.content());

        profile.get().addBlog(blog);
        var example=profileRepository.save(profile.get());

        example.toString();
        return 1L;
    }
}
