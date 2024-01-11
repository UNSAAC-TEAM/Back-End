package org.unsaac.es_bim.iam.domain.model.commands.user;

public record ChangePasswordCommand(Long userId,String newPassword) {
}
