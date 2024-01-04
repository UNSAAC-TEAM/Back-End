package org.unsaac.es_bim.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.iam.domain.model.commands.user.SignInCommand;
import org.unsaac.es_bim.iam.domain.model.commands.user.SignUpCommand;
import org.unsaac.es_bim.iam.domain.services.user.IUserCommandService;

import java.util.Optional;

@Service
public class UserCommandService implements IUserCommandService {
    @Override
    public Optional<User> handle(SignUpCommand command) {
        return Optional.empty();
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        return Optional.empty();
    }
}
