package org.unsaac.es_bim.iam.infrastructure.authorization.sfs.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import org.unsaac.es_bim.iam.infrastructure.persistence.jpa.repositories.UserRepository;

@Service("defaultUserDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    /***
     * This method gets the user from the database and returns a UserDetails object
     * using username.
     * @param username username of the user
     * @return UserDetails object
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImpl.build(user);

    }
}
