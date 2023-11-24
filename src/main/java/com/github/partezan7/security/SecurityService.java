package com.github.partezan7.security;

import com.github.partezan7.data.entity.User;
import com.github.partezan7.data.entity.user.Role;
import com.github.partezan7.data.service.UserService;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SecurityService {

    private final AuthenticationContext authenticationContext;
    private final UserService userService;

    public SecurityService(AuthenticationContext authenticationContext, UserService userService) {
        this.authenticationContext = authenticationContext;
        this.userService = userService;
    }

    public UserDetails getAuthenticatedUser() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class).get();
    }

    public void logout() {
        authenticationContext.logout();
    }

    public boolean isUserHaveRights(Role role) {
        User user = (User) userService
                .loadUserByUsername(getAuthenticatedUser()
                        .getUsername());
        Set<Role> roles = user.getRoles();
        return roles.contains(role);
    }
}