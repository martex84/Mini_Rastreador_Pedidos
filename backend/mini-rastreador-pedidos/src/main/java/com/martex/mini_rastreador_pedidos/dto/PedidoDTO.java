package com.martex.mini_rastreador_pedidos.dto;

import com.martex.mini_rastreador_pedidos.enums.StatusPedido;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class PedidoDTO {
    public record PedidoCriacaoRequest(
            @NotBlank(message = "O endereço é obrigatório")
            String endereco,

            @NotBlank(message = "O id do item é obrigatório")
            String idItem
    ) {
    }

    public record PedidoGetRequest(
            @NotBlank(message = "O id do pedido é obrigatório")
            String idPedido
    ){}

    public record PedidoAtualizacaoRequest(
            @NotBlank(message = "O id do pedido é obrigatório")
            String idPedido,

            @NotBlank(message = "O status do pedido é obrigatório")
            String statusPedido
    ){}

    public record PedidoResponse(
            Long idPedido,
            String endereco,
            StatusPedido status,
            List<String> itens
    ){}
}
