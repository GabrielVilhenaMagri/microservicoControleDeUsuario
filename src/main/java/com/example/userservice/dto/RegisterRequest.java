package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Campo obrigatório")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Campo obrigatório")
    private String username;

    @NotBlank(message = "Campo obrigatório")
    private String password;
}
