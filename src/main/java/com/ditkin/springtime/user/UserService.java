package com.ditkin.springtime.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User CreateUser(UserDTO dto) {
        // validate
        // map
        User user = new User("");
        userRepository.save(user);
        return null; // TODO
    }
}
