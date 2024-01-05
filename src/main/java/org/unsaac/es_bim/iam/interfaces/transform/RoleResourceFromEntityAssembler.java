package org.unsaac.es_bim.iam.interfaces.transform;

import org.unsaac.es_bim.iam.domain.model.entities.Role;
import org.unsaac.es_bim.iam.interfaces.Resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
