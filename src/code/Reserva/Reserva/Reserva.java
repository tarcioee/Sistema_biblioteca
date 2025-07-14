package Reserva;

import java.time.LocalDate;
import Usuarios.IUsuario;
import Livros.Livro;

public class Reserva {
    private IUsuario usuario;
    private Livro livro;
    private LocalDate dataReserva;

    public Reserva(IUsuario usuario, Livro livro, LocalDate dataReserva) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataReserva = dataReserva;
    }

    public IUsuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
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
