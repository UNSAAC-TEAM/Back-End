package org.unsaac.es_bim.iam.interfaces.transform;

import org.unsaac.es_bim.iam.domain.model.commands.user.SignUpCommand;
import org.unsaac.es_bim.iam.domain.model.entities.Role;
import org.unsaac.es_bim.iam.interfaces.Resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        return new SignUpCommand(resource.username(), resource.password(), roles,resource.imageUrl(), resource.firstName(), resource.lastName(), resource.birthDay(),resource.country(), resource.city(), resource.genre(), resource.phoneNumber(), resource.description());
    }
}
