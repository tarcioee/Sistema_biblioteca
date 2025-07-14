// bug resolvido: permitia mesmo usuario tenta fazer x reservas do mesmo livro 
package Comandos;

import java.time.LocalDate;

import Repositorio.Repositorio;
import Reserva.Reserva;
import Usuarios.IUsuario;
import Livros.ILivro;

public class ReservaComando implements IComando {

    @Override
public void executar(String[] args) {
    if (args.length != 2) {
        System.out.println("Uso: res <codigoUsuario> <codigoLivro>");
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

    // ✅ Verifica se o usuário já reservou esse livro
    boolean jaReservado = usuario.getReservas().stream()
        .anyMatch(r -> r.getLivro().getCodigo() == codLivro);

    if (jaReservado) {
        System.out.println("Usuário já possui uma reserva para este livro.");
        return;
    }

    Reserva reserva = new Reserva(usuario, livro, LocalDate.now());
    livro.adicionarReserva(reserva);
    usuario.getReservas().add(reserva);

    System.out.println("Reserva registrada com sucesso.");
}

}
