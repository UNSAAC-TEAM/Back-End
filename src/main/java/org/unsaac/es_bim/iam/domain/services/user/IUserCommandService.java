package org.unsaac.es_bim.iam.domain.services.user;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.iam.domain.model.commands.user.*;

import java.util.Optional;

public interface IUserCommandService {
    Optional<User> handle(SignUpCommand command);

    /*If the credentials are correct, returns the user and token*/
    Optional<ImmutablePair<User,String>> handle(SignInCommand command);
    Long handle(ChangeEmailCommand command);
    Long handle(ChangePasswordCommand command);

    Long handle(EditUserCommand command);
}
