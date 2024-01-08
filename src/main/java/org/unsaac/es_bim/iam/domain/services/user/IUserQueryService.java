package org.unsaac.es_bim.iam.domain.services.user;

import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.iam.domain.model.queries.user.GetAllUsersQuery;
import org.unsaac.es_bim.iam.domain.model.queries.user.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IUserQueryService {

    List<User> handle(GetAllUsersQuery query);

    Optional<User> handle(GetUserByIdQuery query);
}
