package com.ditkin.springtime;

import com.ditkin.springtime.user.User;
import com.ditkin.springtime.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@SpringBootApplication
public class SpringtimeApplication {


    @PostConstruct
    public void init() {
        // Test creating a user on startup
    }

    @RequestMapping("/")
    public String index(@AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            return "Hello, " + principal.getFullName();
        }
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringtimeApplication.class, args);
    }
}
