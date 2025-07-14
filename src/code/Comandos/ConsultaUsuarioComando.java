//Revisado, tudo certo
package Comandos;

import java.time.format.DateTimeFormatter;

import Repositorio.Repositorio;
import Usuarios.IUsuario;
import Emprestimo.Emprestimo;
import Reserva.Reserva;

public class ConsultaUsuarioComando implements IComando {

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void executar(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: usu <codigoUsuario>");
            return;
        }

        int codUsuario = Integer.parseInt(args[0]);
        IUsuario usuario = Repositorio.getInstancia().buscarUsuarioPorCodigo(codUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("Empréstimos:");
        for (Emprestimo emp : usuario.getEmprestimos()) {
            String status = emp.estaEmAndamento() ? "Em andamento" : "Finalizado";
            System.out.print("- Titulo: " + emp.getExemplar().getLivro().getTitulo());
            System.out.print(" | Data: " + emp.getDataEmprestimo().format(fmt));
            System.out.print(" | Status: " + status);

            if (emp.getDataDevolucaoReal() != null) {
                System.out.print(" | Devolvido em: " + emp.getDataDevolucaoReal().format(fmt));
            } else {
                System.out.print(" | Devolução Prevista para: " + emp.getDataDevolucaoPrevista().format(fmt));
            }

            System.out.println();
        }

        System.out.println("Reservas:");
        for (Reserva res : usuario.getReservas()) {
            System.out.println("- Titulo: " + res.getLivro().getTitulo() + " | Data da reserva: " + res.getDataReserva().format(fmt));
        }
    }
}
