import {
  createContext,
  useContext,
  useMemo,
  useState,
  type ReactNode,
} from "react";
import { useNavigate } from "react-router-dom";
import { url } from "@/routes/AppRoutes";

interface AuthContextType {
  token: string | null;
  atualizarToken: (valor: string) => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: Readonly<{ children: ReactNode }>) {
  const [token, setToken] = useState<string | null>(null);

  const navigate = useNavigate();

  function atualizarToken(valor: string) {
    if (!valor) throw new Error("Falha na obtenção do token!");

    setToken("Bearer " + valor);

    navigate(url.pedidos);
  }

  const contextValue = useMemo(
    () => ({
      token,
      atualizarToken,
    }),
    [token],
  );

  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);

  if (!context)
    throw new Error("O contexto deve ser utilizado dentro do provider");

  return context;
}
