package com.ditkin.springtime.user;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static java.util.Collections.EMPTY_SET;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        // Test creating a user on startup
        Role testRole = new Role("ADMIN");
        Role savedRole = roleRepository.save(testRole);
        Set<Role> roles = new HashSet<>();
        roles.add(savedRole);
        User testUser = new User("daviditkin", "ditkin@gmail.com", roles);
        userRepository.save(testUser);
        System.out.println("Test user created successfully");
    }

    @RequestMapping("/users")
    public List<String> getUserNames(@AuthenticationPrincipal DefaultOAuth2User principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println(authentication.getPrincipal());
        }
        if (principal != null) {
            return Collections.singletonList("foo");
        }
        return userRepository.findAll().stream().map(User::getName).collect(Collectors.toList());
    }
}
