import { useAuth } from "@/shared/application/AuthProvider";
import registro from "../infra/registro";
import { useState } from "react";
import "./style.css";

export default function Registro() {
  const [email, setEmail] = useState<string>("");
  const [senha, setSenha] = useState<string>("");
  const [nome, setNome] = useState<string>("");

  const { atualizarToken } = useAuth();

  return (
    <div id="container_main" className="container_main_login">
      <h1>Registrar Usuário</h1>
      <div className="container_input">
        <span className="span_input">Nome</span>
        <input
          className="input"
          onChange={(evento) => setNome(evento.target.value)}
          value={nome}
        />
      </div>
      <div className="container_input">
        <span className="span_input">E-mail</span>
        <input
          className="input"
          type="email"
          onChange={(evento) => setEmail(evento.target.value)}
          value={email}
        />
      </div>
      <div className="container_input">
        <span className="span_input">Senha</span>
        <input
          className="input"
          type="password"
          onChange={(evento) => setSenha(evento.target.value)}
          value={senha}
        />
      </div>
      <div className="container_buttons">
        <div className="container_button_main">
          <button
            onClick={() => registro({ nome, email, senha, atualizarToken })}
          >
            Registrar
          </button>
        </div>
      </div>
    </div>
  );
}
