import type { Pedido } from "@/features/pedidos/model/pedido";
import "../style.css";

interface Props {
  pedido: Pedido;
}

export default function PedidoUnitario(props: Readonly<Props>) {
  return (
    <div className="container_pedido_minimo">
      <div className="informacao_unitaria_header">
        <div>
          <span className="informacao_principal">#</span>
          <span className="informacao_principal">{props.pedido.idPedido}</span>
        </div>
      </div>
      <div className="container_informacao_separada">
        <span className="titulo_informacao">Endereço</span>
        <span>{props.pedido.endereco}</span>
      </div>
      <div className="container_informacao_separada">
        <span className="titulo_informacao">Status</span>
        <span>{props.pedido.status}</span>
      </div>
    </div>
  );
}
