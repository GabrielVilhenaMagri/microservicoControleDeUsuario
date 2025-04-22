package com.example.userservice.controller;


import com.example.userservice.dto.AuthenticationRequest;
import com.example.userservice.dto.AuthenticationResponse;
import com.example.userservice.dto.RegisterRequest;
import com.example.userservice.security.service.authServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@AllArgsConstructor
public class authController {


    private final authServices authServices;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request){
        return ResponseEntity.ok(authServices.register(request));
    }

    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authServices.login(request));
    }

}
