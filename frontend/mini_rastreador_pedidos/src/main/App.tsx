// import Login from "./features/login/ui/login";
import { AuthProvider } from "@/shared/application/AuthProvider";
import AppRoutes from "@/routes/AppRoutes";

function App() {
  return (
    <AuthProvider>
      <AppRoutes />
    </AuthProvider>
  );
}

export default App;
