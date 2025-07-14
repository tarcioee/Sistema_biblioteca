//aparentemente ok
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
        System.out.println("Reservas: " + livro.getReservas().size());

        if (!livro.getReservas().isEmpty()) {
            System.out.println("Usuários com reserva:");
            for (Reserva r : livro.getReservas()) {
                System.out.println("- " + r.getUsuario().getNome());
            }
        }

        System.out.println("Exemplares:");
        for (IExemplar ex : livro.getExemplares()) {
            String status = ex.estaDisponivel() ? "Disponível" : "Emprestado";
            System.out.print("Exemplar " + ex.getCodigo() + " - " + status);

            if (!ex.estaDisponivel() && ex.getUsuarioAtual() != null) {
                System.out.print(" (Emprestado para: " + ex.getUsuarioAtual().getNome() + ")");
            }

            System.out.println();
        }
    }
}
