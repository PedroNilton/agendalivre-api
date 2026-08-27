# AgendaLivre API 

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Clean Architecture](https://img.shields.io/badge/Clean_Architecture-✓-blue?style=for-the-badge)

O **AgendaLivre** é um sistema de agendamento inteligente construído no formato SaaS (Software as a Service) voltado para profissionais autônomos (barbeiros, terapeutas, professores particulares, etc.). Ele permite o gerenciamento completo de profissionais, serviços, clientes e automatiza a marcação de horários, garantindo que **conflitos de agenda (double booking)** nunca aconteçam.

Este projeto foi construído focando em boas práticas de Engenharia de Software, aplicando rigorosamente os princípios da **Clean Architecture** (Arquitetura Limpa), separando as regras de negócio de frameworks e banco de dados.

##  Tecnologias Utilizadas

A stack tecnológica foi escolhida visando o padrão corporativo (Enterprise) de desenvolvimento backend:

- **Java 17** (LTS)
- **Spring Boot 3.2**
- **Spring Data JPA** (Hibernate)
- **H2 Database** (Desenvolvimento/Testes rápidos)
- **PostgreSQL** (Pronto para Produção)
- **Lombok** (Redução de Boilerplate)
- **Jakarta Validation** (Validação de DTOs)

##  Arquitetura (Clean Architecture)

O código foi meticulosamente desenhado para não depender do Spring Boot em seu núcleo (Domain). As camadas estão divididas em:

1. **Domain (`domain`)**: Entidades puras do negócio (ex: `Agendamento`, `Profissional`) e regras de domínio usando Orientação a Objetos. Zero dependências externas.
2. **Application (`application`)**: Casos de uso (`UseCases`) que orquestram a aplicação, e `Gateways` (interfaces) que definem contratos para acesso a dados.
3. **Infrastructure (`infrastructure`)**: Detalhes de implementação, como Entidades JPA (`@Entity`), Spring Data Repositories e configuração de Banco de Dados.
4. **Presentation (`presentation`)**: Controllers REST (`@RestController`) e DTOs, além de tratamento global de erros (`GlobalExceptionHandler`).

##  Funcionalidades

- ✅ Cadastro de Profissionais e Clientes
- ✅ Cadastro de Serviços com duração e preço
- ✅ Sistema de Agendamento Inteligente
- ✅ **Controle de Concorrência:** O algoritmo valida cruzamentos de horários para impedir que dois clientes marquem serviços que coincidam no tempo para o mesmo profissional.
- ✅ Tratamento Global de Exceções (ControllerAdvice) retornando JSONs amigáveis em erros de negócio.

##  Como rodar o projeto localmente

1. Certifique-se de ter o **Java 17** e o **Maven** instalados em sua máquina.
2. Clone este repositório:
   ```bash
   git clone https://github.com/PedroNilton/agendalivre-api.git
   ```
3. Navegue até a pasta do projeto:
   ```bash
   cd agendalivre-api
   ```
4. Execute o projeto usando Maven:
   ```bash
   mvn spring-boot:run
   ```
5. A API estará rodando em `http://localhost:8080`.
6. Você pode acessar o banco de dados em memória (H2 Console) em `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:agendalivredb`, User: `sa`, Password: *em branco*).

##  Endpoints Principais

A API responde no path `/api/v1`. 

* `POST /api/v1/profissionais`: Cadastra um profissional
* `POST /api/v1/clientes`: Cadastra um cliente
* `POST /api/v1/servicos`: Cadastra um serviço vinculado a um profissional
* `POST /api/v1/agendamentos`: Cria um agendamento validando conflitos de horário

**Exemplo de Payload de Agendamento (`POST /api/v1/agendamentos`):**
```json
{
  "profissionalId": "uuid-do-profissional",
  "clienteId": "uuid-do-cliente",
  "servicoId": "uuid-do-servico",
  "dataHoraInicio": "2026-09-01T14:30:00"
}
```

---
*Desenvolvido com foco em boas práticas e Código Limpo.* ☕
