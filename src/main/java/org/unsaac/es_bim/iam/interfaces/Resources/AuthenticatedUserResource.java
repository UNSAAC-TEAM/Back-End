package org.unsaac.es_bim.iam.interfaces.Resources;

public record AuthenticatedUserResource(String token,Long userId,String firstName,String lastName,String email,String imageUrl, String roll) {
}
