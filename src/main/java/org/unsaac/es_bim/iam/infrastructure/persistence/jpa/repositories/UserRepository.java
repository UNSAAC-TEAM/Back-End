package org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /*find by username*/
    Optional<User> findByEmail(String username);

    /* if user exists by username*/

    boolean existsByEmail(String username);
}
