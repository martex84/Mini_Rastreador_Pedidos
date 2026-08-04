import Login from "@/features/login/ui/login";
import Registro from "@/features/login/ui/registro";
import CadastrarPedidos from "@/features/pedidos/ui/CadastrarPedido";
import Pedido from "@/features/pedidos/ui/Pedido";
import Pedidos from "@/features/pedidos/ui/Pedidos";
import { Route, Routes } from "react-router-dom";

export const url = {
  login: "/",
  pedidos: "/pedidos",
  pedido: "/pedido",
  registro: "/registro",
  cadastrarPedido: "cadastrarPedido",
};

export default function AppRoutes() {
  return (
    <Routes>
      <Route path={url.login} element={<Login />} />
      <Route path={url.pedidos} element={<Pedidos />} />
      <Route path={url.pedido} element={<Pedido />} />
      <Route path={url.registro} element={<Registro />} />
      <Route path={url.cadastrarPedido} element={<CadastrarPedidos />} />
    </Routes>
  );
}
