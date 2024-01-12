package org.unsaac.es_bim.iam.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.application.internal.outboundservices.hashing.HashingService;
import org.unsaac.es_bim.iam.application.internal.outboundservices.services.TokenService;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.iam.domain.model.commands.user.*;
import org.unsaac.es_bim.iam.domain.services.user.IUserCommandService;
import org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.unsaac.es_bim.profiles.domain.model.aggregates.Profile;

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
        if (userRepository.existsByEmail(command.email())) throw new RuntimeException("Username already exists");

        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role name not found"))).toList();
        var user = new User(command.email(), hashingService.encode(command.password()), roles);
        Profile newProfile=new Profile( command.firstName(), command.lastName(),  command.country(),  command.phoneNumber());
        user.setProfile(newProfile);
        newProfile.setAccount(user);
        userRepository.save(user);
        return userRepository.findByEmail(command.email());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateTokenWithId(user.get().getEmail(),user.get().getId());
        return Optional.of(ImmutablePair.of(user.get(), token));

    }

    @Override
    public Long handle(ChangeEmailCommand command) {
        var user=userRepository.findById(command.userId());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        user.get().updateEmail(command.newEmail());
        this.userRepository.save(user.get());
        return 1L;
    }

    @Override
    public Long handle(ChangePasswordCommand command) {
        var user=userRepository.findById(command.userId());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        user.get().updatePassword(hashingService.encode(command.newPassword()));
        return 1L;
    }

    @Override
    public Long handle(EditUserCommand command) {
        var user=userRepository.findById(command.userId());
        if(user.isEmpty()){
            throw  new RuntimeException("User Not found");
        }
        return 1L;
    }
}
