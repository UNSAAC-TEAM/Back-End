package org.unsaac.es_bim.iam.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.application.internal.outboundservices.hashing.HashingService;
import org.unsaac.es_bim.iam.application.internal.outboundservices.services.TokenService;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.iam.domain.model.commands.user.SignInCommand;
import org.unsaac.es_bim.iam.domain.model.commands.user.SignUpCommand;
import org.unsaac.es_bim.iam.domain.services.user.IUserCommandService;
import org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandService implements IUserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username())) throw new RuntimeException("Username already exists");

        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role name not found"))).toList();
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));

    }
}
