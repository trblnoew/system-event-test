package com.example.serverexam.controller;

import com.example.serverexam.entity.User;
import com.example.serverexam.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 전체 조회
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // 생성
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
