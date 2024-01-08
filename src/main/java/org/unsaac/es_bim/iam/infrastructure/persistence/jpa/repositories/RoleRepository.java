package org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsaac.es_bim.iam.domain.model.entities.Role;
import org.unsaac.es_bim.iam.domain.model.valueObjects.Roles;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    /*find role by name*/
    Optional<Role> findByName(Roles name);

    /*Check if role exists*/
    boolean existsByName(Roles name);
}
