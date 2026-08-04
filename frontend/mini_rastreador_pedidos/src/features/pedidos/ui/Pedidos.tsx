import type { Pedido } from "@/features/pedidos/model/pedido";
import { getPedidos } from "../infra/pedidos";
import { useState } from "react";
import { useAuth } from "@/shared/application/AuthProvider";
import "./style.css";
import PedidoUnitario from "@/features/pedidos/ui/components/pedidoUnitario";
import { useNavigate } from "react-router-dom";
import { url } from "@/routes/AppRoutes";

export default function Pedidos() {
  const [listaPedidos, setListaPedidos] = useState<Pedido[] | undefined>(
    undefined,
  );

  const navigate = useNavigate();

  const { token } = useAuth();

  function gerarLista(listaPedidos: Pedido[] | undefined) {
    if (listaPedidos) {
      return listaPedidos.map((item) => {
        const pedido = item as Pedido;

        return <PedidoUnitario pedido={pedido} key={item.idPedido} />;
      });
    } else return <></>;
  }

  return (
    <div
      id="container_main"
      className="container_main_pedidos"
      style={{ justifyContent: "start" }}
    >
      <div className="container_buttons">
        <div className="container_button_main">
          <button onClick={() => navigate("../" + url.cadastrarPedido, {})}>
            Cadastrar Pedido
          </button>
        </div>
        <div className="container_button_main">
          <button
            onClick={async () => setListaPedidos(await getPedidos(token))}
          >
            Buscar Pedidos
          </button>
        </div>
      </div>
      {gerarLista(listaPedidos)}
    </div>
  );
}
