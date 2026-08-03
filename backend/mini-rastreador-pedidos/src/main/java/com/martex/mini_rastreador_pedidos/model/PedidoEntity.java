package com.martex.mini_rastreador_pedidos.model;

import com.martex.mini_rastreador_pedidos.enums.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.martex.mini_rastreador_pedidos.model.UserEntity;

@Entity
@Table(name = "tb_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    @NotBlank
    private String endereco;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public PedidoEntity (UserEntity usuario, String endereco, StatusPedido status){
        this.usuario = usuario;
        this.endereco = endereco;
        this.status = status;
    }
}
