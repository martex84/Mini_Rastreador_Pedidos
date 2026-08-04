import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./styleMain.css";
import App from "./App.tsx";
import { BrowserRouter } from "react-router-dom";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <BrowserRouter>
      <div id="tela_principal">
        <App />
      </div>
    </BrowserRouter>
  </StrictMode>,
);
