# Sistema_biblioteca

Estrutura do repositório:

src/
│
├── app.java
│
├── code/
│   ├── Comandos/
│   │   ├── Icomando.java
│   │   ├── ReservaComando.java
│   │   ├── DevolucaoComando.java
│   │   ├── EmprestimoComando.java
│   │   ├── ConsultaLivroComando.java
│   │   ├── ConsultaUsuarioComando.java
│   │   ├── ConsultaNotificacoesComando.java
│   │   ├── RegistrarObservadorComando.java
│   │   └── SairComando.java
│   │
│   ├── Emprestimo/
│   │   ├── Emprestimo.java
│   │   └── Estrategias/
│   │       ├── EmprestimoAluno.java
│   │       ├── EmprestimoProfessor.java
│   │       └── IEstrategiaEmprestimo.java
│   │
│   ├── Livros/
│   │   ├── Exemplar.java
│   │   ├── IExemplar.java
│   │   ├── ILivro.java
│   │   ├── Livro.java
│   │   └── EstadoExemplar/
│   │       ├── IExemplarEstado.java
│   │       ├── ExemplarDisponivel.java
│   │       └── ExemplarEmprestado.java
│   │
│   ├── Observadores/
│   │   ├── ISujeito.java
│   │   └── IObservador.java
│   │
│


Padrão singleton (obrigatório): deve ser usado pra classe Repositório só ter uma instância.
Padrão command (obrigatório): Cada ação do usuário (empréstimo, devolução, reserva, consulta, observação) é encapsulada em uma classe de comando, facilitando a extensão e manutenção do sistema.
Padrão strategy (obrigatório): pra parte das diferentes estratégias de empréstimo pra cada tipo de usuário
Padrão observer (obrigatório): pra parte de professor poder observar livros e tals
Padrão state (óbvio): pra um exemplar com os estados disponivel e emprestado