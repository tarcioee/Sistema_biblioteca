//bugado em caso de teste comum
package Comandos;

import Repositorio.Repositorio;
import Usuarios.IUsuario;
import Emprestimo.Emprestimo;

import java.time.LocalDate;

public class DevolucaoComando implements IComando {

    @Override
    public void executar(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: dev <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[0]);
        int codLivro = Integer.parseInt(args[1]);

        IUsuario usuario = Repositorio.getInstancia().buscarUsuarioPorCodigo(codUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Emprestimo emprestimo = usuario.getEmprestimos().stream()
            .filter(e -> e.estaEmAndamento() && e.getExemplar().getCodigo() / 100 == codLivro)
            .findFirst()
            .orElse(null);

        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado.");
            return;
        }

        emprestimo.registrarDevolucao(LocalDate.now());
        System.out.println("Devolução registrada com sucesso.");
    }
}
