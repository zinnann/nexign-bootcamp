package ru.grak.crm.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.grak.crm.entity.User;
import ru.grak.crm.security.jwt.UserDetailsImplFactory;
import ru.grak.crm.service.UserService;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Users with username: %s not found", username)));

        return UserDetailsImplFactory.create(user);
    }
}
