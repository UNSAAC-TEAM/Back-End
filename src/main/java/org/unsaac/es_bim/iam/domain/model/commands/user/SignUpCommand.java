package org.unsaac.es_bim.iam.domain.model.commands.user;

import org.unsaac.es_bim.iam.domain.model.entities.Role;

import java.util.Date;
import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles, String imageUrl, String firstName, String lastName, Date birthDay, String country, String city, String genre, String phoneNumber, String description) {
}
