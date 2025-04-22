package com.example.userservice.controller;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.model.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserRepository userRepository;

    // Injeção via construtor
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user) {
        try {
            User savedUser = userService.createUser(user);
            return ResponseEntity.ok(savedUser);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable @Valid String email) {
        User userFound = userService.getUserByEmail(email);
        return ResponseEntity.ok(userFound);

    }





}
