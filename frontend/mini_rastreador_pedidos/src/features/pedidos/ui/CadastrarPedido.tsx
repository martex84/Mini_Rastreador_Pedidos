import { useState } from "react";
import type { Item } from "../model/item";
import { cadastrarPedidos } from "../infra/pedidos";
import { useAuth } from "@/shared/application/AuthProvider";
import "./style.css";
import { useNavigate } from "react-router-dom";
import { url } from "@/routes/AppRoutes";

export default function CadastrarPedidos() {
  const [item, setItem] = useState<string>("");
  const [endereco, setEndereco] = useState<string>("");

  const navigate = useNavigate();

  const { token } = useAuth();

  return (
    <div id="container_main" className="container_main_pedidos">
      <h1>Cadastrar Item</h1>
      <div className="container_input">
        <span className="span_input">Id do Item</span>
        <input
          className="input"
          onChange={(evento) => setItem(evento.target.value)}
          value={item}
        />
      </div>
      <div className="container_input">
        <span className="span_input">Endereço da Entrega</span>
        <input
          className="input"
          onChange={(evento) => setEndereco(evento.target.value)}
          value={endereco}
        />
      </div>
      <div className="container_buttons">
        <div className="container_button_main">
          <button
            onClick={async () => {
              await cadastrarPedidos(token, { endereco, idItem: item });

              navigate("../" + url.pedidos);
            }}
          >
            Criar
          </button>
        </div>
        <div className="container_button_main">
          <button onClick={() => navigate("../" + url.pedidos)}>Voltar</button>
        </div>
      </div>
    </div>
  );
}
