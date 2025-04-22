package com.example.userservice.security.service;


import com.example.userservice.dto.AuthenticationRequest;
import com.example.userservice.dto.AuthenticationResponse;
import com.example.userservice.dto.RegisterRequest;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class authServices {


    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;



    public AuthenticationResponse register(RegisterRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // criptografia!
        user.setUsername(request.getUsername());

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthenticationResponse(token);
    }
    public AuthenticationResponse login(AuthenticationRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (request.getEmail(),
                request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario nao foi achado!"));

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthenticationResponse(token);
    }
}
