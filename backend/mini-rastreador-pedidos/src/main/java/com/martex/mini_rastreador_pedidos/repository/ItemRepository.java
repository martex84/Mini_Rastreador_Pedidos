package com.martex.mini_rastreador_pedidos.repository;

import com.martex.mini_rastreador_pedidos.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    ItemEntity findByNome(String nome);
}
