package com.martex.mini_rastreador_pedidos.service;

import com.martex.mini_rastreador_pedidos.dto.PedidoDTO;
import com.martex.mini_rastreador_pedidos.enums.StatusPedido;
import com.martex.mini_rastreador_pedidos.model.PedidoEntity;
import com.martex.mini_rastreador_pedidos.model.PedidoItemEntity;
import com.martex.mini_rastreador_pedidos.repository.ItemRepository;
import com.martex.mini_rastreador_pedidos.repository.PedidoItemRepository;
import com.martex.mini_rastreador_pedidos.repository.PedidoRepository;
import com.martex.mini_rastreador_pedidos.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UserRepository userRepository;
    private final PedidoItemRepository pedidoItemRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void criarPedido(PedidoDTO.PedidoCriacaoRequest pedidoCriacaoRequest, String idUsuario) {
        var usuario = userRepository.findById(Long.parseLong(idUsuario)).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var pedidoEntity = new PedidoEntity(usuario, pedidoCriacaoRequest.endereco(), StatusPedido.EM_PREPARO);

        var pedidoSalvo = pedidoRepository.save(pedidoEntity);

        var listaItem = pedidoCriacaoRequest.idItem().split("-");

        if (itemRepository.count() < listaItem.length) throw new Error("Não foi encontrado todos os itens!");

        for (String s : listaItem) {
            var item = itemRepository.findById(Long.parseLong(s)).orElseThrow(() -> new RuntimeException("Item não encontrado"));

            var pedidoItemEntity = new PedidoItemEntity(pedidoSalvo, item);

            pedidoItemRepository.save(pedidoItemEntity);
        }
    }

    @Transactional
    public void atualizarStatusPedido(String idUsuario, String idPedido, String statusPedidoAtual){
        if(!StatusPedido.statusValido(statusPedidoAtual)) throw new RuntimeException("O status do pedido é inválido!");

        var pedido = pedidoRepository.findById(Long.parseLong(idPedido)).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o pedido desejado!"));

        if(!pedido.getUsuario().getId().toString().equals(idUsuario)) throw new RuntimeException("O pedido atual não pertence ao usuário");

        pedido.setStatus(StatusPedido.valueOf(statusPedidoAtual));
    }

    public PedidoDTO.PedidoResponse getPedido(String idUsuario, PedidoDTO.PedidoGetRequest pedidoGetRequest){
        var pedido =  pedidoRepository.findById(Long.parseLong(pedidoGetRequest.idPedido())).orElseThrow(() -> new RuntimeException("O usuário não tem pedido cadastrado!"));

        if(!pedido.getUsuario().getId().toString().equals(idUsuario)) throw new RuntimeException("O pedido atual não é do usuário!");

        if (itemRepository.count() == 0) throw new RuntimeException("Nenhum item cadastrado");

        List<PedidoItemEntity> listaPedidoItem = pedidoItemRepository.findByPedidoId(pedido.getId());

        List<String> itens = new ArrayList<>();

        for (var pedidoItem : listaPedidoItem) {
            itens.add(pedidoItem.getItem().getNome());
        }

        return new PedidoDTO.PedidoResponse(
                pedido.getId(),
                pedido.getEndereco(),
                pedido.getStatus(),
                itens
        );
    }

    public List<PedidoDTO.PedidoResponse> getPedidos(String idUsuario) {
        List<PedidoDTO.PedidoResponse> pedidoResponses = new ArrayList<>();

        if (itemRepository.count() == 0) throw new RuntimeException("Nenhum item cadastrado");

        var listaPedidos = pedidoRepository.findAllByUsuarioId(Long.parseLong(idUsuario));

        if (listaPedidos.isEmpty()) throw new RuntimeException("O usuário não tem pedidos cadastrados");

        for (var pedido : listaPedidos) {
            List<PedidoItemEntity> listaPedidoItem = pedidoItemRepository.findByPedidoId(pedido.getId());

            List<String> itens = new ArrayList<>();

            for (var pedidoItem : listaPedidoItem) {
                itens.add(pedidoItem.getItem().getNome());
            }

            pedidoResponses.add(
                    new PedidoDTO.PedidoResponse(
                            pedido.getId(),
                            pedido.getEndereco(),
                            pedido.getStatus(),
                            itens
                    )
            );
        }

        return pedidoResponses;
    }
}
