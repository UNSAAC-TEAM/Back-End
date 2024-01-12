package org.unsaac.es_bim.iam.domain.model.commands.user;

import java.util.Date;

public record EditUserCommand(Long userId, String imageUrl, String firstName, String lastName,
                              String country,String city,String gender,String phoneNumber,String description) {
}
