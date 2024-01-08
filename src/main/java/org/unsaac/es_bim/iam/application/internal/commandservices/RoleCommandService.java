package org.unsaac.es_bim.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.domain.model.commands.role.SeedRolesCommand;
import org.unsaac.es_bim.iam.domain.model.entities.Role;
import org.unsaac.es_bim.iam.domain.model.valueObjects.Roles;
import org.unsaac.es_bim.iam.domain.services.role.IRoleCommandService;
import org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.Arrays;

@Service
public class RoleCommandService implements IRoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role ->{
            if(!roleRepository.existsByName(role)){
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        } );
    }
}
