package com.martex.mini_rastreador_pedidos.service;

import com.martex.mini_rastreador_pedidos.model.UserEntity;
import com.martex.mini_rastreador_pedidos.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.martex.mini_rastreador_pedidos.dto.UserDTO;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public boolean registrarUsuario(UserDTO.RegisterRequest registerRequest) {
        var teste = userRepository.findByEmail(registerRequest.email());

        if (userRepository.findByEmail(registerRequest.email()) != null)
            throw new RuntimeException("O e-mail já está cadastrado no sistema!");

        try {
            var entidadeUser = new UserEntity(registerRequest.email(), registerRequest.password(), registerRequest.nome());

            userRepository.save(entidadeUser);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean buscarUsuario(UserDTO.LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.email());

        if (user == null) throw new RuntimeException("Usuário não encontrado!");

        return user.getPassword().equalsIgnoreCase(loginRequest.password());
    }
}
