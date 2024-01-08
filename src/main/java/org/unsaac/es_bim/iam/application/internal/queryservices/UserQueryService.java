package org.unsaac.es_bim.iam.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.iam.domain.model.queries.user.GetAllUsersQuery;
import org.unsaac.es_bim.iam.domain.model.queries.user.GetUserByIdQuery;
import org.unsaac.es_bim.iam.domain.services.user.IUserQueryService;
import org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService implements IUserQueryService {
    private final UserRepository userRepository;
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }
}
