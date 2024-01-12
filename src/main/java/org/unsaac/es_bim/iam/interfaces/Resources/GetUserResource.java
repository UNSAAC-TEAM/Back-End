package org.unsaac.es_bim.iam.interfaces.Resources;

import org.unsaac.es_bim.iam.domain.model.entities.Role;
import org.unsaac.es_bim.profiles.domain.model.aggregates.Profile;

import java.util.Set;

public record GetUserResource(String email, Set<Role> roles, Profile profile) {
}
