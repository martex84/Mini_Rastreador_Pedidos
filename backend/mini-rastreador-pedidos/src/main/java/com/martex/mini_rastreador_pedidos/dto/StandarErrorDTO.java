package com.martex.mini_rastreador_pedidos.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandarErrorDTO {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
