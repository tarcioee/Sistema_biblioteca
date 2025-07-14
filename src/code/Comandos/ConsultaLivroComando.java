//Revisado, tudo certo
package Comandos;

import Repositorio.Repositorio;
import Livros.ILivro;
import Livros.IExemplar;
import Reserva.Reserva;

public class ConsultaLivroComando implements IComando {

    @Override
    public void executar(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: liv <codigoLivro>");
            return;
        }

        int codLivro = Integer.parseInt(args[0]);
        ILivro livro = Repositorio.getInstancia().buscarLivroPorCodigo(codLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Quantidade Reservas: " + livro.getReservas().size());

        if (!livro.getReservas().isEmpty()) {
            System.out.println("Nome dos Usuários com reserva:");
            for (Reserva r : livro.getReservas()) {
                System.out.println("- " + r.getUsuario().getNome());
            }
        }

        System.out.println("Exemplares:");
        for (IExemplar ex : livro.getExemplares()) {
            String status = ex.estaDisponivel() ? "Disponível" : "Emprestado";
            System.out.print("Exemplar " + ex.getCodigo() + " - " + status);

            if (!ex.estaDisponivel() && ex.getUsuarioAtual() != null) {
                var usuario = ex.getUsuarioAtual();
                System.out.print(" (Emprestado para: " + usuario.getNome());

                // procura o empréstimo ativo do exemplar
                var emprestimo = usuario.getEmprestimos().stream()
                    .filter(e -> e.estaEmAndamento() && e.getExemplar().getCodigo() == ex.getCodigo())
                    .findFirst()
                    .orElse(null);

                if (emprestimo != null) {
                    System.out.print(", Data de empréstimo: " + emprestimo.getDataEmprestimo());
                    System.out.print(", Data prevista de devolução: " + emprestimo.getDataDevolucaoPrevista());
                }

                System.out.print(")");
            }

            System.out.println();
        }
    }
}
