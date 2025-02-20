# 📦StockManager📦
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/Joao-Silva01/GameVerse/blob/main/LICENSE)
<p>O StockManager é um sistema de gerenciamento de estoque e pedidos desenvolvido em Java utilizando Spring Boot. Seu principal objetivo é permitir a criação, edição e exclusão de pedidos com controle de status: 🟡Pending, 🟢Completed e 🔴Canceled.</p>
<p>OBS: Vai ter a parte visual deste projeto (Front-End).</p>

## Funcionalidades
### Pedidos (SalesOrder):
- Criação, edição e exclusão de pedidos.
- Status dinâmico: Pending, Completed e Canceled.
- Associação de múltiplos produtos a um pedido.
   
### Clientes:
- Dois tipos de clientes:
  - INDIVIDUAL_CLIENT: Cadastrado com CPF (validação personalizada).
  - CORPORATE_CLIENT: Cadastrado com CNPJ (validação personalizada).
- Atributos adicionais: telefones, endereços e outros dados pessoais.

### Produtos e Categorias:
- Produtos podem ser associados a categorias.
- Uma categoria pode conter vários produtos.

### Segurança e Autenticação:
- Implementação do Spring Security com autenticação via JWT.
- Cadastro de contas para clientes com senha criptografada.
- Controle de acesso baseado em Roles, permitindo ações conforme a permissão do usuário.

### Validações e Exceções:
- Validações personalizadas para entidades.
- Tratamento de exceções com mensagens customizadas.
- Conversores de classes para reduzir a complexidade do código.

## Modelo Conceitual
<img src="https://github.com/user-attachments/assets/70a66541-7aef-45ea-b6ca-71d92f810881" alt="descrição" width="800"/>

## 👨‍💻Tecnologias Utilizadas👨‍💻
- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Flyway (Migrations para versionamento do banco de dados)
- Maven (Gerenciador de dependências)
- MySQL (Banco de dados relacional)

## 🚀Executação do Projeto🚀
<p>Pra executar este projeto tenho que lembrar para verificar e alterar algumas propriedades no Application.properties e DatabaseSeeder</p>

Para rodar é necessário ter o [Maven](https://maven.apache.org/download.cgi) instalado!!

### Application.properties
```
spring.datasource.url=${DB_URL}
spring.datasource.username= ${DB_USERNAME}
spring.datasource.password= ${DB_PASSWORD}
config.security.token = ${TOKEN_KEY}
```
### DatabaseSeeder
```
var passwordAdmin = passwordEncoder.encode(System.getenv("ADMIN_PASSWORD"));
```

### Pronto?? Então vamos lá!

1. Clone o repositório:
   ```
   git clone https://github.com/Joao-Silva01/StockManager_Back.git
   ```
2. Entre no repositório:
   ```
   cd StockManager_Back/
   ```

3. Builda o Projeto:
   ```
   mvn clean spring-boot:run
   ```
