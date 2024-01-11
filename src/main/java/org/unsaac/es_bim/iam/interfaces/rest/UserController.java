package org.unsaac.es_bim.iam.interfaces.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unsaac.es_bim.iam.domain.model.queries.user.GetUserByIdQuery;
import org.unsaac.es_bim.iam.domain.services.user.IUserQueryService;
import org.unsaac.es_bim.iam.interfaces.Resources.GetUserResource;

@RestController
@RequestMapping(value = "api/v1/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final IUserQueryService userQueryService;

    public UserController(IUserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResource> getUserById(GetUserByIdQuery query){
        var user= this.userQueryService.handle(query);
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            var response= new GetUserResource(user.get().getEmail(),user.get().getRoles(),user.get().getProfile());
            return ResponseEntity.ok(response);
        }
    }
}
