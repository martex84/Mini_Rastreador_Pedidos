package com.martex.mini_rastreador_pedidos.service;

import com.martex.mini_rastreador_pedidos.dto.ItemDTO;
import com.martex.mini_rastreador_pedidos.model.ItemEntity;
import com.martex.mini_rastreador_pedidos.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public boolean createItem(ItemDTO.ItemRequest item) {
        if(itemRepository.findByNome(item.nome()) != null) throw new RuntimeException("O item já foi cadastrado!");

        try {
            var itemEntity = new ItemEntity(item.nome());
            itemRepository.save(itemEntity);

            return true;
        }
        catch (RuntimeException e){
            return false;
        }
    }

    public List<ItemDTO.ItemResponse> getItems(){
        var itens = itemRepository.findAll();

        List<ItemDTO.ItemResponse> itemResponses = new ArrayList<>();

        for(ItemEntity itemEntity : itens) {
            var item = new ItemDTO.ItemResponse(
                    itemEntity.getId().toString(),
                    itemEntity.getNome()
            );

            itemResponses.add(item);
        }

        return itemResponses;
    }

}
