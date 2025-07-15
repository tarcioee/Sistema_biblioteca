package Usuarios;

import SistemaBiblioteca.IEntidadeBiblioteca;
import Emprestimo.Emprestimo;
import Emprestimo.Estrategias.IEstrategiaEmprestimo;

import java.util.List;

import Reserva.Reserva;

public interface IUsuario extends IEntidadeBiblioteca {
    String getNome();
    IEstrategiaEmprestimo getEstrategiaEmprestimo();
    int getLimiteEmprestimos();
    int getLimiteDiasEmprestimo();
    List<Emprestimo> getEmprestimos();
    List<Reserva> getReservas();
    void adicionarEmprestimo(Emprestimo emprestimo);
    void adicionarReserva(Reserva reserva);
    boolean possuiEmprestimoAtivoDoLivro(int codigoLivro);
}
