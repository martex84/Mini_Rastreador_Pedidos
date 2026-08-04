import axios from "axios";

interface DadosLogin {
  email: string;
  senha: string;
  atualizarToken: (valor: string) => void;
}

const api = axios.create({
  baseURL: "http://localhost:8080",
});

export default function login(dados: DadosLogin) {
  api
    .post("/login", {
      email: dados.email,
      password: dados.senha,
    })
    .then((retorno) => {
      const valor = retorno.data;

      if (!valor) throw new Error("Falha ao captar o token");

      dados.atualizarToken(valor);
    })
    .catch((erro) => console.error(erro));
}
