package org.unsaac.es_bim.iam.domain.services.role;

import org.unsaac.es_bim.iam.domain.model.commands.role.SeedRolesCommand;

public interface IRoleCommandService {
    void handle(SeedRolesCommand command);
}
