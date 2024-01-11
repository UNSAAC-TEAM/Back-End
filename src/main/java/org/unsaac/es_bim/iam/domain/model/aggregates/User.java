package org.unsaac.es_bim.iam.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.unsaac.es_bim.iam.domain.model.entities.Role;
import org.unsaac.es_bim.profiles.domain.model.aggregates.Profile;

import java.util.*;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    //Map One account one profile
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Profile profile;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
        this.roles = new HashSet<>();
    }
    public void setProfile(Profile profile){
        this.profile=profile;
    }
    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
        this.createdAt=new Date();
        this.updatedAt=new Date();
    }
    public void updateEmail(String email){
        this.email=email;
    }
    public void updatePassword(String password){
        this.password=password;
    }
    public User(String email, String password, List<Role> roles) {
        this(email, password);
        addRoles(roles);
    }

    /**
     * Add a role to the user
     * @param role the role to add
     * @return the user with the new role
     */
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public User addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;



}
