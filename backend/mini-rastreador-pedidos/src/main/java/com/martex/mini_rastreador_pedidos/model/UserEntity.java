package com.martex.mini_rastreador_pedidos.model;

/*
- UserName
- Password
*/

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "tb_login")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    //Constructor
    public UserEntity(String email, String password, String nome) {
        this.nome = nome;
        this.email = email;
        this.password = password;
    }
}
