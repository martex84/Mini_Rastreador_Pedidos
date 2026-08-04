package com.martex.mini_rastreador_pedidos.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.martex.mini_rastreador_pedidos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtTokenService{

    @Value("martex.mini_rastreador_pedidos")
    private String nomeProjeto;

    @Value("Teste")
    private String secret;

    private final UserRepository userRepository;

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return  JWT.require(algorithm)
                    .withIssuer(nomeProjeto)
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException expection){
            return "";
        }
    }

    public String criarToken(String email){
        var user = userRepository.findByEmail(email);

        if(user == null) throw new RuntimeException("Não foi possível localizar o e-mail do usuário!");

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer(nomeProjeto)
                    .withSubject(user.getId().toString())
                    .withExpiresAt(captarDataFinalToken())
                    .sign(algorithm);
        }
        catch (JWTCreationException expection){
            return "";
        }
    }

    private Instant captarDataFinalToken(){
        return Instant.now().plus(Duration.ofHours(1));
    }
}
