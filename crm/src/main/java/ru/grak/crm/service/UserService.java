package ru.grak.crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.crm.entity.User;
import ru.grak.crm.repository.RoleRepository;
import ru.grak.crm.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


//    public User register(User user) {
//        Role roleUser = roleRepository.findByName("ROLE_USER");
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(userRoles);
//
//        User registeredUser = userRepository.save(user);
//
//        return registeredUser;
//    }

    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            return null;
        }
        return result;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
