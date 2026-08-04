import { url } from "@/routes/AppRoutes";

export default function validarToken(token: string | null) {
  if (!token || token.trim() === "") {
    const urlAtual = new URL(window.location.href);

    window.location.replace(urlAtual.origin + url.login);

    alert("O token está inválido!");
  }
}
