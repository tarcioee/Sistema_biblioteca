//desbugado em caso de teste comum, checar tentando quebrar
package Comandos;

import Repositorio.Repositorio;
import Usuarios.IUsuario;
import Livros.ILivro;
import Livros.IExemplar;
import Emprestimo.Emprestimo;

import java.time.LocalDate;

public class EmprestimoComando implements IComando {

    @Override
    public void executar(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: emp <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[0]);
        int codLivro = Integer.parseInt(args[1]);

        Repositorio repo = Repositorio.getInstancia();
        IUsuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);
        ILivro livro = repo.buscarLivroPorCodigo(codLivro);

        if (usuario == null || livro == null) {
            System.out.println("Usuário ou livro não encontrado.");
            return;
        }

        if (!usuario.getEstrategiaEmprestimo().podeEmprestar(usuario, livro)) {
            System.out.println(usuario.getEstrategiaEmprestimo().motivoFalha(usuario, livro));
            return;
        }

        IExemplar exemplarDisponivel = livro.getExemplares().stream()
            .filter(IExemplar::estaDisponivel)
            .findFirst()
            .orElse(null);

        if (exemplarDisponivel == null) {
            System.out.println("Nenhum exemplar disponível.");
            return;
        }

        // Executar o empréstimo
        exemplarDisponivel.emprestar(usuario);
        Emprestimo emprestimo = new Emprestimo(usuario, exemplarDisponivel, LocalDate.now());
        usuario.getEmprestimos().add(emprestimo);

        // Remove reserva se houver
        livro.removerReserva(usuario);

        System.out.println("Empréstimo realizado com sucesso.");
    }
}
