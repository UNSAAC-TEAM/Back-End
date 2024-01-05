package org.unsaac.es_bim.iam.domain.model.commands.user;

import org.unsaac.es_bim.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, String role) {
}
