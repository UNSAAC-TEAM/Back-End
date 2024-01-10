package org.unsaac.es_bim.iam.interfaces.Resources;

import java.util.Date;
import java.util.List;

public record SignUpResource(String username, String password, List<String> roles, String imageUrl, String firstName, String lastName, Date birthDay, String country, String city, String genre, String phoneNumber, String description) {
}
