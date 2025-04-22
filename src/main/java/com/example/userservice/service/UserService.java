package com.example.userservice.service;

import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole() == null){
            user.setRole(Role.CLIENTE);
        }
        return userRepository.save(user);
    }

    public User getAllUsers(){
        return (User) userRepository.findAll();
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o email: " + email));
    }



}
