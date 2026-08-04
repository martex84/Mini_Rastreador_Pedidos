package com.martex.mini_rastreador_pedidos.dto;

import jakarta.validation.constraints.NotBlank;

public class ItemDTO {
    public record ItemRequest(
            @NotBlank(message = "O nome do item é obrigatório")
            String nome
    ){}

    public record ItemResponse(
            String id,
            String nome
    ){}
}
