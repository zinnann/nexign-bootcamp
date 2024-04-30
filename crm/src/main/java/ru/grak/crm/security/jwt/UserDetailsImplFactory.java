package ru.grak.crm.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.grak.crm.entity.Role;
import ru.grak.crm.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public final class UserDetailsImplFactory {

    private UserDetailsImplFactory() {
    }

    public static UserDetailsImpl create(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                getGrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> getGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().getAuthority()))
                .collect(Collectors.toList());
    }
}
