package org.unsaac.es_bim.profiles.domain.services;

import org.unsaac.es_bim.profiles.domain.model.commands.ChangeImageUrlByUserIdCommand;
import org.unsaac.es_bim.profiles.domain.model.commands.EditProfileByUserIdCommand;

public interface IProfileCommandServices {
    Long handle(EditProfileByUserIdCommand command);
    Long handle(ChangeImageUrlByUserIdCommand command);
}
