package org.unsaac.es_bim.iam.infrastructure.authorization.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;

import java.util.Collection;

@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {


    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;

    private final boolean enabled;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public static UserDetailsImpl build(User user) {
        var authorities = user.getRoles().stream().map(role -> role.getStringName())
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new UserDetailsImpl(user.getEmail(), user.getPassword(), authorities);
    }

}
