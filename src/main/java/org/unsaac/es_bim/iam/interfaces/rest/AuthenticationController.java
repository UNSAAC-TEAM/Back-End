package org.unsaac.es_bim.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.unsaac.es_bim.iam.domain.model.commands.user.SignInCommand;

import org.unsaac.es_bim.iam.domain.services.user.IUserCommandService;
import org.unsaac.es_bim.iam.interfaces.Resources.AuthenticatedUserResource;
import org.unsaac.es_bim.iam.interfaces.Resources.SignUpResource;

import org.unsaac.es_bim.iam.interfaces.Resources.UserResource;
import org.unsaac.es_bim.iam.interfaces.transform.SignUpCommandFromResourceAssembler;
import org.unsaac.es_bim.iam.interfaces.transform.UserResourceFromEntityAssembler;


@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final IUserCommandService userCommandService;
    public AuthenticationController(IUserCommandService userCommandService){
        this.userCommandService=userCommandService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInCommand command){
        var authenticatedUser = userCommandService.handle(command);

        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AuthenticatedUserResource response=new AuthenticatedUserResource(authenticatedUser.get().getLeft().getUsername(),authenticatedUser.get().getRight());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource){
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);

    }

}
