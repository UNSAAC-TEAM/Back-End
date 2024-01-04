package org.unsaac.es_bim.iam.domain.services.role;

import org.unsaac.es_bim.iam.domain.model.entities.Role;
import org.unsaac.es_bim.iam.domain.model.queries.role.GetAllRolesQuery;
import org.unsaac.es_bim.iam.domain.model.queries.role.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface IRoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
