package com.ditkin.springtime.user;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         User user = (User) o;
         return id != null && id.equals(user.id);
     }

     @Override
     public int hashCode() {
         return id != null ? id.hashCode() : 0;
     }

     protected User() {}

    public User(String name) {
        setName(name);
    }
}
