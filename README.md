# Sistema de Gerenciamento de Biblioteca

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-Command%2C%20Strategy%2C%20Observer-blue?style=for-the-badge)

Um sistema de gerenciamento de biblioteca via linha de comando, desenvolvido em Java, com uma arquitetura robusta e desacoplada baseada em Padrões de Projeto (Design Patterns).

## ✨ Funcionalidades

* Realizar empréstimos e devoluções de livros.
* Fazer reservas de títulos.
* Consultar o estado detalhado de livros e seus exemplares.
* Consultar o histórico de empréstimos e reservas de um usuário.
* Registrar interesse em um livro para receber notificações (Padrão Observer).
* Políticas de empréstimo flexíveis que variam conforme o tipo de usuário (Aluno, Professor, etc.).

## 🏛️ Arquitetura e Padrões de Projeto

O núcleo do projeto é sua arquitetura, que promove baixo acoplamento e alta coesão através do uso de múltiplos padrões de projeto.

### Camadas da Aplicação
O sistema é dividido em camadas de responsabilidade bem definidas:
1.  **Terminal (Apresentação):** Responsável pela interação com o usuário.
2.  **Comandos (Controle):** Processa as entradas do usuário usando o Padrão Command.
3.  **Service (Serviço):** Orquestra a lógica de negócio, atuando como um Facade.
4.  **Domínio:** Contém as entidades de negócio e a implementação dos padrões.
5.  **Repositório (Acesso a Dados):** Abstrai a fonte de dados.

### Padrões Implementados

* ### Padrão Command
    * **Propósito:** Encapsula cada solicitação do usuário (emprestar, devolver, etc.) como um objeto.
    * **Implementação:** A interface `IComando` e suas classes concretas (`EmprestimoComando`, `DevolucaoComando`) separam o invocador (`Entrada`) da ação.

* ### Padrão Strategy
    * **Propósito:** Permite que as regras de empréstimo (o algoritmo) variem dinamicamente de acordo com o tipo do usuário.
    * **Implementação:** A interface `IEstrategiaEmprestimo` e suas implementações (`EmprestimoAluno`, `EmprestimoProfessor`) definem diferentes políticas que são associadas a cada `IUsuario`, facilitando implementação de novas estratégias.

* ### Padrão State
    * **Propósito:** Modifica o comportamento de um objeto quando seu estado interno muda.
    * **Implementação:** A classe `Exemplar` altera seu estado entre `ExemplarDisponivel` e `ExemplarEmprestado`, mudando o resultado de métodos como `estaDisponivel()` sem usar condicionais.

* ### Padrão Observer
    * **Propósito:** Define um mecanismo de assinatura para notificar múltiplos objetos sobre eventos.
    * **Implementação:** A classe `Livro` (Sujeito) notifica os usuários do tipo `Professor` (Observadores) quando o número de reservas para um título aumenta.

* ### Padrão Singleton
    * **Propósito:** Garante que a classe `Repositorio`, que gerencia o acesso aos dados, tenha apenas uma instância global.

## 🚀 Como Executar

### Pré-requisitos
* JDK (Java Development Kit) instalado.

### Passos
1.  **Na pasta raiz 'src/', execute o comando:**
    javac *.java code/*/*.java code/*/*/*.java

2.  **Ainda na pasta 'src/', execute:**
    java app

## ⌨️ Comandos Disponíveis

| Comando | Argumentos                      | Descrição                                                    |
| :------ | :------------------------------ | :----------------------------------------------------------- |
| `emp`   | `<codigoUsuario> <codigoLivro>` | Realiza o empréstimo de um livro para um usuário.            |
| `dev`   | `<codigoUsuario> <codigoLivro>` | Realiza a devolução de um livro.                             |
| `res`   | `<codigoUsuario> <codigoLivro>` | Cria uma reserva de um livro para um usuário.                |
| `liv`   | `<codigoLivro>`                 | Consulta os detalhes, exemplares e reservas de um livro.     |
| `usu`   | `<codigoUsuario>`               | Consulta os empréstimos e reservas de um usuário.            |
| `obs`   | `<codigoUsuario> <codigoLivro>` | Registra um usuário como observador de um livro.             |
| `ntf`   | `<codigoUsuario>`               | Exibe a quantidade de notificações que um usuário recebeu.   |
| `sai`   | (nenhum)                        | Encerra a execução do sistema.                               |


### Estrutura do repositório
src/
│
├── App.java
│
├── README.md
|
└── code/
    ├── Biblioteca/
    │   └── IEntidadeBiblioteca.java
    │
    ├── Comandos/
    │   ├── IComando.java
    │   ├── ReservaComando.java
    │   ├── DevolucaoComando.java
    │   ├── EmprestimoComando.java
    │   ├── ConsultaLivroComando.java
    │   ├── ConsultaUsuarioComando.java
    │   ├── ConsultaNotificacoesComando.java
    │   ├── RegistrarObservadorComando.java
    │   └── SairComando.java
    │
    ├── Emprestimo/
    │   ├── Emprestimo.java
    │   └── Estrategias/
    │       ├── EmprestimoAluno.java
    │       ├── EmprestimoProfessor.java
    │       └── IEstrategiaEmprestimo.java
    │
    ├── Livros/
    │   ├── Exemplar.java
    │   ├── IExemplar.java
    │   ├── ILivro.java
    │   ├── Livro.java
    │   └── EstadoExemplar/
    │       ├── IExemplarEstado.java
    │       ├── ExemplarDisponivel.java
    │       └── ExemplarEmprestado.java
    │
    ├── Observadores/
    │   ├── ISujeito.java
    │   └── IObservador.java
    │
    ├── Repositorio/
    │   └── Repositorio.java
    │
    ├── Reserva/
    │   └── Reserva.java
    │
    ├── Services/
    │   └── BibliotecaService.java
    │
    ├── Terminal/
    │   └── Entrada.java
    │
    └── Usuarios/
        ├── IUsuario.java
        ├── Usuario.java
        ├── AlunoGraduacao.java
        ├── AlunoPosGraduacao.java
        └── Professor.java