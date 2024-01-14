package org.unsaac.es_bim.profiles.interfaces.Resource;

import java.util.Date;

public record ProfileResource(String firstName, String lastName, String imageUrl, Date birthDay,String country,String city,String gender,String phoneNumber, String description) {
}
