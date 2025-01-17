package com.ditkin.springtime.membership;

import java.util.Set;

public record UserDTO(String name, String email, Set<Role> roles) {
}
