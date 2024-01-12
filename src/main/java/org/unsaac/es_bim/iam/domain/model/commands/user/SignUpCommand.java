package org.unsaac.es_bim.iam.domain.model.commands.user;

import org.unsaac.es_bim.iam.domain.model.entities.Role;

import java.util.Date;
import java.util.List;

public record SignUpCommand(String email, String password, List<Role> roles,  String firstName, String lastName, String country,  String phoneNumber) {
}
