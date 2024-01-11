package org.unsaac.es_bim.profiles.interfaces.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unsaac.es_bim.profiles.domain.model.queries.GetProfileByUserId;
import org.unsaac.es_bim.profiles.domain.services.IProfileQueryServices;
import org.unsaac.es_bim.profiles.interfaces.Resource.ProfileResource;

@RestController
@RequestMapping(value = "/api/v1/profile",produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
    private final IProfileQueryServices profileQueryServices;

    public ProfileController(IProfileQueryServices profileQueryServices) {
        this.profileQueryServices = profileQueryServices;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable Long userId){
        var query=new GetProfileByUserId(userId);
        var profile=this.profileQueryServices.handle(query);
        if(profile.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var response=new ProfileResource(profile.get().getFirstName(),profile.get().getLastName(),profile.get().getProfileImageUrl(),profile.get().getBirthDay(),profile.get().getCountry(),profile.get().getCity(),profile.get().getGender().toString(),profile.get().getDescription());
        return ResponseEntity.ok(response);
    }
}
