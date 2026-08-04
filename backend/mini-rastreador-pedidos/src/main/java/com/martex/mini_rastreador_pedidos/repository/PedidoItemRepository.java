package com.martex.mini_rastreador_pedidos.repository;

import com.martex.mini_rastreador_pedidos.model.PedidoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItemEntity, Long> {
    List<PedidoItemEntity> findByPedidoId(Long pedidoId);
}
