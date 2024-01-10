package org.unsaac.es_bim.iam.interfaces.Resources;

import java.util.List;

public record UserResource(Long id, String email, List<String> roles) {
}
