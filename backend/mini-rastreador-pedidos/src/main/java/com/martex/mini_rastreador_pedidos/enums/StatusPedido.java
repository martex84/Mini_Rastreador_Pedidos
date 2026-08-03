package com.martex.mini_rastreador_pedidos.enums;

import java.util.Arrays;

public enum StatusPedido {
    RECEBIDO,
    EM_PREPARO,
    SAIU_PARA_ENTREGA,
    ENTREGUE,
    CANCELADO;

    public static boolean statusValido(String status){
        var valor = StatusPedido.valueOf(status.toUpperCase());

        return !valor.toString().isBlank();
    }
}
