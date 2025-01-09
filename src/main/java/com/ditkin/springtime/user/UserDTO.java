package com.ditkin.springtime.user;

import java.util.Set;

public record UserDTO(String name, String email, Set<Role> roles) {
}
