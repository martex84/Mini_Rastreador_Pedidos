package com.martex.mini_rastreador_pedidos.controller;

import com.martex.mini_rastreador_pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.martex.mini_rastreador_pedidos.dto.PedidoDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/pedido")
    public ResponseEntity<String> criarPedido(@RequestBody PedidoDTO.PedidoCriacaoRequest pedidoCriacaoRequest, @AuthenticationPrincipal String dadosToken) {
        try {
            pedidoService.criarPedido(pedidoCriacaoRequest, dadosToken);

            return ResponseEntity.ok().body("Pedido criado com sucesso!");
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pedido")
    public ResponseEntity<PedidoDTO.PedidoResponse> listarPedido(@AuthenticationPrincipal String dadosToken, @RequestBody PedidoDTO.PedidoGetRequest pedidoGetRequest) {
        try {
            return ResponseEntity.ok(pedidoService.getPedido(dadosToken, pedidoGetRequest));
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoDTO.PedidoResponse>> getPedidos(@AuthenticationPrincipal String dadosToken) {
        try {
           return ResponseEntity.ok(pedidoService.getPedidos(dadosToken));
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/pedido/atualizar_status")
    public ResponseEntity<String> atualizarStatus(@RequestBody PedidoDTO.PedidoAtualizacaoRequest pedidoAtualizacaoRequest, @AuthenticationPrincipal String dadosToken) {
        try {
            pedidoService.atualizarStatusPedido(
                    dadosToken,
                    pedidoAtualizacaoRequest.idPedido(),
                    pedidoAtualizacaoRequest.statusPedido()
            );

            return ResponseEntity.ok("Pedido atualizado com sucesso!");
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
