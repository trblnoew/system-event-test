package com.example.serverexam.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // 기본 생성자 (JPA 필수)
    protected User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
