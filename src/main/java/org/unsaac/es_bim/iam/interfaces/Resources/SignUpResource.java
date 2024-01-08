package org.unsaac.es_bim.iam.interfaces.Resources;

import java.util.List;

public record SignUpResource(String username, String password, List<String> roles) {
}
