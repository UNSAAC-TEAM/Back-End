package org.unsaac.es_bim.iam.domain.model.commands.user;

public record ChangeEmailCommand(Long userId,String newEmail,String password) {
}
