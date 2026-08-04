package com.martex.mini_rastreador_pedidos.controller;

import com.martex.mini_rastreador_pedidos.exception.ResourceNotFoundException;
import com.martex.mini_rastreador_pedidos.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.martex.mini_rastreador_pedidos.dto.ItemDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<String> criarItem(@RequestBody @Valid ItemDTO.ItemRequest item){
        try {
            itemService.createItem(item);

            return ResponseEntity.ok("Item criado com sucesso!");
        }
        catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO.ItemResponse>> getItem(){
        try {
            var itemCaptado = itemService.getItems();

            return ResponseEntity.ok(itemCaptado);
        }
        catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
