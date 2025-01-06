package com.ditkin.springtime;

import com.ditkin.springtime.user.User;
import com.ditkin.springtime.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@SpringBootApplication
public class SpringtimeApplication {

    private final UserRepository userRepo;


    @PostConstruct
    public void init() {
        // Test creating a user on startup
        User testUser = new User("test-user");
        userRepo.save(testUser);
        System.out.println("Test user created successfully");
    }

    SpringtimeApplication(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/users")
    public List<String> getUserNames() {
        return userRepo.findAll().stream().map(User::getName).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringtimeApplication.class, args);
    }
}
