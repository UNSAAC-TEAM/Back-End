package org.unsaac.es_bim.iam.domain.services.user;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.iam.domain.model.commands.user.SignInCommand;
import org.unsaac.es_bim.iam.domain.model.commands.user.SignUpCommand;

import java.util.Optional;

public interface IUserCommandService {
    Optional<User> handle(SignUpCommand command);

    /*If the credentials are correct, returns the user and token*/
    Optional<ImmutablePair<User,String>> handle(SignInCommand command);
}
