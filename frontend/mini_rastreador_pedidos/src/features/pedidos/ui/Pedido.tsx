import { alterarStatus, getPedido } from "@/features/pedidos/infra/pedidos";
import type { Pedido as TipoPedido } from "../model/pedido";
import { useEffect, useState } from "react";
import { useAuth } from "@/shared/application/AuthProvider";
import "./style.css";

export default function Pedido() {
  const [pedido, setPedido] = useState<TipoPedido | undefined>(undefined);
  const [idPedido, setIdPedido] = useState<string | undefined>(undefined);

  const { token } = useAuth();

  return (
    <div id="container_main" className="container_main_pedidos">
      <div>
        <div>
          <span>Id Pedido</span>
          <input
            onChange={(event) => setIdPedido(event.target.value)}
            value={idPedido}
          />
        </div>
        <div>
          <button
            onClick={async () => setPedido(await getPedido(token, idPedido))}
          >
            Buscar
          </button>
        </div>
        <span>Pedido</span>
        <span>{pedido?.idPedido}</span>
      </div>
      <div>
        <span>Endereço</span>
        <span>{pedido?.endereco}</span>
      </div>
      <div>
        <span>status</span>
        <span>{pedido?.status}</span>
        <div>
          <button
            onClick={async () => alterarStatus(token, idPedido, pedido?.status)}
          >
            Alterar Status
          </button>
        </div>
      </div>
    </div>
  );
}
