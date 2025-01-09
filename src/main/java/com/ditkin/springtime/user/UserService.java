package com.ditkin.springtime.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO: should the createUser take a UserDTO?  Should it be a UserCommand... ??
    public User createUser(UserDTO dto) {
        // validate using jakarta or bean
        // map using mapstruct
        User user = new User(dto.name(), dto.email(), dto.roles());

        Optional<User> foo = userRepository.findByEmail("foo");
        if (foo.isPresent()) {
        }
        return userRepository.save(user);
    }
}
