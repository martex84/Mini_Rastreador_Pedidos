package com.martex.mini_rastreador_pedidos.controller;

import com.martex.mini_rastreador_pedidos.dto.UserDTO;
import com.martex.mini_rastreador_pedidos.service.JwtTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.martex.mini_rastreador_pedidos.service.UserService;

//TODO: FAZER TESTE UNITÁRIO
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserDTO.LoginRequest loginRequest) {
        try {
            if (!userService.buscarUsuario(loginRequest)) throw new RuntimeException("Usuário ou Senha incorretos");

            return ResponseEntity.ok(jwtTokenService.criarToken(loginRequest.email()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDTO.RegisterRequest registerRequest) {
        try {
            var criacaoUsuario = userService.registrarUsuario(registerRequest);

            if (!criacaoUsuario) throw new Error("Falha na criação do usuário!");

            return ResponseEntity.ok(jwtTokenService.criarToken(registerRequest.email()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
