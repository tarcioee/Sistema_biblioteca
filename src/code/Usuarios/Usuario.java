package Usuarios;

import Emprestimo.Emprestimo;

import java.util.ArrayList;
import java.util.List;

import Reserva.Reserva;

public abstract class Usuario implements IUsuario {
    protected int codigo;
    protected String nome;
    protected int contadorNotificacoes;
    protected List<Emprestimo> emprestimos;
    protected List<Reserva> reservas;

    public Usuario(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.contadorNotificacoes = 0;
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    @Override
    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    @Override
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public boolean possuiEmprestimoAtivoDoLivro(int codigoLivro) {
        for (Emprestimo e : emprestimos) {
            if (!e.isFinalizado() && e.getExemplar().getLivro().getCodigo() == codigoLivro) {
                return true;
            }
        }
        return false;
    }
    
    public int getNotificacoesRecebidas() {
    return contadorNotificacoes;
}


    public abstract int getLimiteEmprestimos();
    public abstract int getLimiteDiasEmprestimo();
}
