package com.martex.mini_rastreador_pedidos.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    public record LoginRequest(
            @NotBlank(message = "O email é obrigatório!")
            String email,

            @NotBlank(message = "O password é obrigatório!")
            String password
    ) {
    }

    public record RegisterRequest(
            @NotBlank(message = "O email é obrigatório!")
            String email,

            @NotBlank(message = "O password é obrigatório!")
            String password,

            @NotBlank(message = "O nome é obrigatório!")
            String nome
    ){}
}