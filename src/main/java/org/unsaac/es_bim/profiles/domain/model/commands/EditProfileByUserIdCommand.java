package org.unsaac.es_bim.profiles.domain.model.commands;

public record EditProfileByUserIdCommand(Long userId, String firstName, String lastName, String imageUrl, String country, String city, String gender, String phoneNumber, String description){
}
