import { useAuth } from "@/shared/application/AuthProvider";
import login from "../infra/login";
import { useState } from "react";
import { Link } from "react-router-dom";
import { url } from "@/routes/AppRoutes";
import "./style.css";

export default function Login() {
  const [email, setEmail] = useState<string>("");
  const [senha, setSenha] = useState<string>("");

  const { atualizarToken } = useAuth();

  return (
    <div id="container_main" className="container_main_login">
      <h1>Login</h1>
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
            onClick={() =>
              login({ email, senha, atualizarToken: atualizarToken })
            }
          >
            Login
          </button>
        </div>
        <div>
          <Link to={url.registro}>Registrar</Link>
        </div>
      </div>
    </div>
  );
}
