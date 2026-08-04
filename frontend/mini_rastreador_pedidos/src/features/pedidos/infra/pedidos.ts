import type { Pedido } from "@/features/pedidos/model/pedido";
import axios from "axios";
import validarToken from "@/features/pedidos/domain/validarToken";

interface DadosCadastro {
  idItem: string;
  endereco: string;
}

const api = axios.create({
  baseURL: "http://localhost:8080",
});

export async function cadastrarPedidos(
  token: string | null,
  dados: DadosCadastro,
) {
  validarToken(token);
  try {
    await api.post(
      "/pedido",
      {
        idItem: dados.idItem,
        endereco: dados.endereco,
      },
      {
        headers: {
          Authorization: token,
        },
      },
    );
  } catch (error) {
    console.error(error);

    alert("Falha ao criar o item!");
  }
}

export async function getPedido(
  token: string | null,
  idPedido: string | undefined,
): Promise<Pedido | undefined> {
  let pedido;

  validarToken(token);

  try {
    const pedido = (
      await api.get("/pedido" + "?id=" + idPedido, {
        headers: {
          Authorization: token,
        },
      })
    ).data as Pedido;

    if (!pedido) throw new Error("Não foi encontrado o pedido do usuário");

    return pedido;
  } catch (e) {
    console.error(e);

    return pedido;
  }
}

export async function getPedidos(
  token: string | null,
): Promise<Pedido[] | undefined> {
  let pedidos;

  validarToken(token);

  try {
    pedidos = (
      await api.get("/pedidos", {
        headers: {
          Authorization: token,
        },
      })
    ).data as Pedido[];

    if (!Array.isArray(pedidos) || pedidos?.length == 0)
      throw new Error("Não foi encontrados pedidos");

    return pedidos;
  } catch (e) {
    console.error(e);

    return pedidos;
  }
}

export async function alterarStatus(
  token: string | null,
  idPedido: string | undefined,
  statusPedido: string | undefined,
) {
  validarToken(token);

  await api.post(
    "/pedido/atualizar_status",
    {
      idPedido,
      statusPedido,
    },
    {
      headers: {
        Authorization: token,
      },
    },
  );
}
