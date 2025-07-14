package Reserva;

import java.time.LocalDate;
import Usuarios.IUsuario;
import Livros.ILivro;

public class Reserva {
    private IUsuario usuario;
    private ILivro livro;
    private LocalDate dataReserva;

    public Reserva(IUsuario usuario, ILivro livro, LocalDate dataReserva) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataReserva = dataReserva;
    }

    public IUsuario getUsuario() {
        return usuario;
    }

    public ILivro getLivro() {
        return livro;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    @Override
    public String toString() {
        return String.format("Reserva de %s em %s", usuario.getNome(), dataReserva.toString());
    }
}
