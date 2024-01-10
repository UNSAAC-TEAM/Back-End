package org.unsaac.es_bim.profiles.domain.model.commands;

import java.util.Date;

public record CreateProfileCommand(String imageUrl, String firstName, String lastName, Date birthDay,String country,String city,String gender,String phoneNumber,String description) {
}
