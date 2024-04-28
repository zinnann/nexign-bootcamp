package ru.grak.crm.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.grak.crm.dto.RegisterRequest;
import ru.grak.crm.entity.RoleEnum;
import ru.grak.crm.entity.User;
import ru.grak.crm.exceptions.AbonentAlreadyExistException;
import ru.grak.crm.repository.RoleRepository;
import ru.grak.crm.repository.UserRepository;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registration(RegisterRequest registerRequest) throws RoleNotFoundException {
        User users = new User(
                registerRequest.getUsername(),
                encodePassword(registerRequest));

        if (userRepository.findByUsername(registerRequest.getUsername()).isEmpty()) {
            setUserRole(users);
            userRepository.save(users);
        } else {
            throw new AbonentAlreadyExistException("Abonent with username: " + registerRequest.getUsername()
                    + " already exist");
        }
    }

    private String encodePassword(RegisterRequest registerRequest) {
        return bCryptPasswordEncoder.encode(registerRequest.getPassword());
    }

    private void setUserRole(User users) throws RoleNotFoundException {
        users.setRoles(List.of(
                roleRepository.findByName(RoleEnum.USER).orElseThrow(() ->
                        new RoleNotFoundException("Role doesn't exist"))));
    }
}
