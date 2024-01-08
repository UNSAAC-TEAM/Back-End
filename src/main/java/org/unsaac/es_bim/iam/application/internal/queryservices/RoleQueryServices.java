package org.unsaac.es_bim.iam.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.domain.model.entities.Role;
import org.unsaac.es_bim.iam.domain.model.queries.role.GetAllRolesQuery;
import org.unsaac.es_bim.iam.domain.model.queries.role.GetRoleByNameQuery;
import org.unsaac.es_bim.iam.domain.model.valueObjects.Roles;
import org.unsaac.es_bim.iam.domain.services.role.IRoleQueryService;
import org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleQueryServices implements IRoleQueryService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
}
