package com.ditkin.springtime.user;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        // Test creating a user on startup
        User testUser = new User("test-user");
        userRepository.save(testUser);
        System.out.println("Test user created successfully");
    }

    @RequestMapping("/users")
    public List<String> getUserNames(@AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            return Collections.singletonList("foo");
        }
        return userRepository.findAll().stream().map(User::getName).collect(Collectors.toList());
    }
}
