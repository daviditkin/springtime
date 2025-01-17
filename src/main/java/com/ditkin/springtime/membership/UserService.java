package com.ditkin.springtime.membership;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO dto) {
        // validate using jakarta or bean
        // map using mapstruct
        User user = new User(dto.name(), dto.email(), dto.roles());

        Optional<User> foo = userRepository.findByEmail("foo");

        return userRepository.save(user);
    }
}
