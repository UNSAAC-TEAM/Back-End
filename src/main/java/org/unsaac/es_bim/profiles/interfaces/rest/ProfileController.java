package org.unsaac.es_bim.profiles.interfaces.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unsaac.es_bim.profiles.domain.model.commands.ChangeImageUrlByUserIdCommand;
import org.unsaac.es_bim.profiles.domain.model.commands.EditProfileByUserIdCommand;
import org.unsaac.es_bim.profiles.domain.model.queries.GetProfileByUserId;
import org.unsaac.es_bim.profiles.domain.services.IProfileCommandServices;
import org.unsaac.es_bim.profiles.domain.services.IProfileQueryServices;
import org.unsaac.es_bim.profiles.interfaces.Resource.ChangeImageProfileResource;
import org.unsaac.es_bim.profiles.interfaces.Resource.EditProfileResource;
import org.unsaac.es_bim.profiles.interfaces.Resource.ProfileResource;

@RestController
@RequestMapping(value = "/api/v1/profile",produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
    private final IProfileQueryServices profileQueryServices;
    private final IProfileCommandServices profileCommandServices;

    public ProfileController(IProfileQueryServices profileQueryServices, IProfileCommandServices profileCommandServices) {
        this.profileQueryServices = profileQueryServices;
        this.profileCommandServices = profileCommandServices;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable Long userId){
        var query=new GetProfileByUserId(userId);
        var profile=this.profileQueryServices.handle(query);
        if(profile.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var response=new ProfileResource(profile.get().getFirstName(),profile.get().getLastName(),profile.get().getProfileImageUrl(),profile.get().getBirthDay(),profile.get().getCountry(),profile.get().getCity(),profile.get().getGender().toString(),profile.get().getPhoneNumber(),profile.get().getDescription());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/edit")
    public ResponseEntity<Long> editProfileByUserId(@PathVariable("userId") Long userId, @RequestBody EditProfileResource body){
        var command=new EditProfileByUserIdCommand(userId,body.firstName(), body.lastName(),  body.birthDay(),body.country(), body.city(), body.gender(), body.phoneNumber(), body.description());
        var response=this.profileCommandServices.handle(command);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{userId}/changeImageProfile")
    public ResponseEntity<Long> changeImageProfileByUserId(@PathVariable("userId")Long userId, @RequestBody ChangeImageProfileResource resource){
        var command=new ChangeImageUrlByUserIdCommand(userId, resource.imageUrl());
        var response=this.profileCommandServices.handle(command);
        return ResponseEntity.ok(response);
    }
}
