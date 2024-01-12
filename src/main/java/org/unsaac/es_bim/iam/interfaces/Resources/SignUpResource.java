package org.unsaac.es_bim.iam.interfaces.Resources;

import java.util.Date;
import java.util.List;

public record SignUpResource(String email, String password, List<String> roles, String firstName, String lastName,  String country,String phoneNumber) {
}
