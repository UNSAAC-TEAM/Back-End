package org.unsaac.es_bim.iam.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.JWT.JwtService;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.iam.domain.model.commands.user.SignInCommand;
import org.unsaac.es_bim.iam.domain.model.commands.user.SignUpCommand;
import org.unsaac.es_bim.iam.domain.model.valueObjects.Roles;
import org.unsaac.es_bim.iam.domain.services.user.IUserCommandService;
import org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandService implements IUserCommandService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if(userRepository.existsByUsername(command.username())){
            throw new RuntimeException("user already exists");
        }
        User user=User.builder().username(command.username())
                .password(passwordEncoder.encode(command.password()))
                .role(Roles.valueOf(command.role())).build();
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.username(),command.password()));
        Optional<User> user=userRepository.findByUsername(command.username());
        if(user.isEmpty()){
            throw new RuntimeException("Account doesn't exist");
        }
        String token=jwtService.getToken(user.get());
        return Optional.of(ImmutablePair.of(user.get(),token));

    }
}
