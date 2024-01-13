package org.unsaac.es_bim.profiles.interfaces.Resource;

import java.util.Date;

public record EditProfileResource(String firstName, String lastName, Date birthDay, String country, String city, String gender, String phoneNumber, String description) {
}
