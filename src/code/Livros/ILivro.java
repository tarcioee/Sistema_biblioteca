package Livros;

import java.util.List;

import Reserva.Reserva;
import Usuarios.IUsuario;
import SistemaBiblioteca.IEntidadeBiblioteca;
import Observadores.ISujeito;

public interface ILivro extends IEntidadeBiblioteca, ISujeito {
    String getTitulo();
    String getEditora();
    String getAutores();
    String getEdicao();
    int getAnoPublicacao();

    void adicionarReserva(Reserva reserva);
    void removerReserva(IUsuario usuario);
    List<Reserva> getReservas();

    List<IExemplar> getExemplares();
    void adicionarExemplar(IExemplar exemplar);
}
