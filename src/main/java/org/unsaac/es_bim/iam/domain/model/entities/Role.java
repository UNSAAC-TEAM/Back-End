package org.unsaac.es_bim.iam.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.unsaac.es_bim.iam.domain.model.valueObjects.Roles;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
public class Role {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    public Role(Roles name){
        this.name=name;
    }
    /* Gets the name of the role as a string*/
    public String getStringName(){
        return name.name();
    }


    /*Get a default role*/
    public static Role getDefaultRole(){
        return new Role(Roles.USER);
    }

    /* Get a role from a string*/
    public static Role toRoleFromName(String name){
        return new Role(Roles.valueOf(name));
    }
    /**
     * Validate the role set if it is null or empty
     * @param roles Role set
     * @return Role set
     */
    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }
        return roles;
    }
}
