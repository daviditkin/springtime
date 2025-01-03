package com.ditkin.springtime;

import com.ditkin.springtime.user.User;
import com.ditkin.springtime.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringtimeApplication {

    private final UserRepository userRepo;

    SpringtimeApplication(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/users")
    public Iterable<User> getUserNames() {
        User aUser = new User("ditkin");
        userRepo.save(aUser);
        return userRepo.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringtimeApplication.class, args);
    }
}
