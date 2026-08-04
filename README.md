# Mini_Rastreador_Pedidos

## 📋 Sobre o Projeto

O projeto é composto por uma **API REST em Java com Spring Boot** para controle de autenticação e gerenciamento de pedidos, integrada a uma interface web desenvolvida em **React (TypeScript)**.

### Status dos Pedidos Suportados:

- `RECEBIDO`: Pedido registrado e aguardando processamento.
- `EM_PREPARO`: Cozinha / Operação preparando o pedido.
- `SAIU_PARA_ENTREGA`: Pedido em trânsito com o entregador.
- `ENTREGUE`: Pedido finalizado com sucesso.
- `CANCELADO`: Pedido cancelado.

---

## 🛠️ Tecnologias Utilizadas

### **Back-end**

- **Linguagem & Framework:** Java 17+ / Spring Boot 4
- **Segurança & Autenticação:** Spring Security + JWT (JSON Web Token)
- **Persistência de Dados:** Spring Data JPA / H2 Database (em modo arquivo)
- **Qualidade & Validações:** Bean Validation (`@Valid`), Lombok

### **Front-end**

- **Bibliotecas & Linguagem:** React, TypeScript, Vite
- **Estilização:** CSS
- **Comunicação HTTP:** Axios (com Interceptor para anexar Token JWT automaticamente)

---

## 🚀 Como Executar a Aplicação

### **Pré-requisitos**

- **JDK 17** ou superior instalado
- **Node.js 18** ou superior e `npm`
- **Git**

---

### **1. Clonar o Repositório**

```bash
git clone <https://github.com/martex84/Mini_Rastreador_Pedidos.git>
cd Mini_Rastreador_Pedidos
```

---

### **2. Executando o Backend (Spring Boot)**

```bash
# Entrar no diretório do backend
cd backend

# Entrar na pasta do projeto
cd mini-rastreador-pedidos

# Executar a aplicação via Maven Wrapper
./mvnw spring-boot:run
```

> O servidor backend estará rodando em: `http://localhost:8080`

---

### **3. Executando o Frontend (React)**

```bash
# Entrar no diretório do frontend (em uma nova janela do terminal)
cd frontend

# Entrar na pasta do projeto
cd mini-rastreador-pedidos

# Instalar as dependências
npm install

# Iniciar o servidor de desenvolvimento
npm run dev
```

> A aplicação frontend estará acessível em: `http://localhost:5173`

---

## 📡 Documentação dos Endpoints (API REST)

### **Autenticação (Rotas Públicas)**

| Método | Endpoint                | Descrição                           | Body de Exemplo (JSON)                                             |
| ------ | ----------------------- | ----------------------------------- | ------------------------------------------------------------------ |
| `POST` | `/register` | Cadastra um novo usuário no sistema e retorna o Token JWT | `{ "nome": "João", "email": "joao@email.com", "password": "123" }` |
| `POST` | `/login`    | Realiza login e retorna o Token JWT | `{ "email": "joao@email.com", "password": "123" }`                 |

### **Pedidos (Rotas Protegidas - Requer `Authorization: Bearer <token>`)**

| Método | Endpoint                | Descrição                           | Body de Exemplo (JSON)                                             |
| ------ | ----------------------- | ----------------------------------- | ------------------------------------------------------------------ |
| `POST` | `/item`  | Cria um novo item | `{"nome": "Nome Item"}` |
| `POST` | `/pedido` | Cria um novo pedido | `{"idItem": "1", "endereco": "Endereço"}` |
| `POST` | `/pedido/atualizar_status` | Atualiza o status de um pedido existente | `{"idPedido": "1", "statusPedido": "SAIU_PARA_ENTREGA"}` |
| `GET` | `/pedidos`  | Lista todos os pedidos cadastrados | SEM BODY |
| `GET` | `/pedido/{id}` | Busca os detalhes de um pedido por ID | SEM BODY |
| `GET` | `/items` | Lista todos os itens | SEM BODY |


## 💡 Decisões Arquiteturais

1. **Separação em Camadas:** Arquitetura limpa estruturada em `Controller`, `Service`, `Repository`, `DTO` e `Entity`, facilitando a manutenção e a legibilidade do código.
2. **Autenticação Stateless (JWT):** Garantia de segurança escalável, permitindo que o React consuma os recursos autenticados via headers HTTP padrão.
3. **Persistência Sem Dependências Externas:** Escolha do H2 embutido durante a execução do servidor, para garantir que a equipe avaliadora possa rodar o projeto imediatamente sem necessidade de subir containers adicionais.

---

## 👤 Autor

Desenvolvido por **Marcelo Teixeira Junior**

- **LinkedIn:** https://linkedin.com/in/martex8
- E-mail: martex849@gmail.com
