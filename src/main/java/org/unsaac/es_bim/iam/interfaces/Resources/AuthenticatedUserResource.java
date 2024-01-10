package org.unsaac.es_bim.iam.interfaces.Resources;

public record AuthenticatedUserResource(String token,Long id,String firstName,String lastName,String email,String imageUrl) {
}
