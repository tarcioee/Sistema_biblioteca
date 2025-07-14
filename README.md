# Sistema_biblioteca

Estrutura do repositório:

Sistemas_Biblioteca/src/
    - code
        - Comandos
            -ConsultaLivroComando.java
            -ConsultaNotificacoesComando.java
            -ConsultaUsuarioComando.java
            -DevolucaoComando.java
            -EmprestimoComando.java
            -Icomando.java
            -RegistrarObservadorComando.java
            -ReservaComando.java
            -SairComando.java
        - Emprestimo
            -Emprestimo.java
            - Estrategias
                -EmprestimoAluno.java
                -EmprestimoProfessor.java
                -IEstrategiaEmprestimo.java
        - Livros
            - EstadoExemplar
                -ExemplarDisponivel.java
                -ExemplarEmprestado.java
                -IExemplarEstado.java
            -Exemplar.java
            -IExemplar.java
            -ILivro.java
            -Livro.java
        - Observadores
            -IObservador.java
            -ISujeito.java
        - Repositorio
            -Repositorio.java
        - Reserva
            -Reserva.java
        - SistemaBiblioteca
            -IEntidadeBiblioteca.java
            -SistemaBiblioteca.java
        - Terminal
            -Entrada.java
            -Saida.java
        - Usuarios
            -AlunoGraduacao.java
            -AlunoPosGraduacao.java
            -IUsuario.java
            -IUsuarioObservador.java
            -Professor.java
            -Usuario.java
        app.java

Padrão singleton (obrigatório): deve ser usado pra classe Repositório só ter uma instância.
Padrão command (obrigatório): Cada ação do usuário (empréstimo, devolução, reserva, consulta, observação) é encapsulada em uma classe de comando, facilitando a extensão e manutenção do sistema.
Padrão strategy (obrigatório): pra parte das diferentes estratégias de empréstimo pra cada tipo de usuário
Padrão observer (obrigatório): pra parte de professor poder observar livros e tals
Padrão state (óbvio): pra um exemplar com os estados disponivel e emprestado
