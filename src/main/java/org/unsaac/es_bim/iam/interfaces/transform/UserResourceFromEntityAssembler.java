package org.unsaac.es_bim.iam.interfaces.transform;

import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.iam.interfaces.Resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(role -> role.getStringName()).toList();
        return new UserResource(user.getId(), user.getEmail(), roles);
    }
}
