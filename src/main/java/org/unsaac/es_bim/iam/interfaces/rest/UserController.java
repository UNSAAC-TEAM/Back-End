package org.unsaac.es_bim.iam.interfaces.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unsaac.es_bim.iam.domain.model.commands.user.ChangeEmailCommand;
import org.unsaac.es_bim.iam.domain.model.commands.user.ChangePasswordCommand;
import org.unsaac.es_bim.iam.domain.model.queries.user.GetUserByIdQuery;
import org.unsaac.es_bim.iam.domain.services.user.IUserCommandService;
import org.unsaac.es_bim.iam.domain.services.user.IUserQueryService;
import org.unsaac.es_bim.iam.interfaces.Resources.ChangeUserEmailResource;
import org.unsaac.es_bim.iam.interfaces.Resources.ChangeUserPasswordResource;
import org.unsaac.es_bim.iam.interfaces.Resources.GetUserResource;

@RestController
@RequestMapping(value = "api/v1/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final IUserQueryService userQueryService;
    private final IUserCommandService userCommandService;

    public UserController(IUserQueryService userQueryService, IUserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
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

    @PutMapping("/{userId}/changeEmail")
    public ResponseEntity<Long> changeUserEmailByUserId(@PathVariable("userId")Long userId,@RequestBody ChangeUserEmailResource emailCommand){
        var command=new ChangeEmailCommand(userId, emailCommand.newEMail(), emailCommand.password());
        var response= this.userCommandService.handle(command);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/changePassword")
    public ResponseEntity<Long> changeUserPasswordByUserId(@PathVariable("userId")Long userId, @RequestBody ChangeUserPasswordResource resource){
        var command=new ChangePasswordCommand(userId, resource.newPassword());
        var response=this.userCommandService.handle(command);
        return ResponseEntity.ok(response);
    }
}
