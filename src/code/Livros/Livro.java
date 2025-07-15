package Livros;

import java.util.ArrayList;
import java.util.List;

import Reserva.Reserva;
import Observadores.IObservador;
import Usuarios.IUsuario;

public class Livro implements ILivro {
    private int codigo;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private int anoPublicacao;

    private List<IExemplar> exemplares;
    private List<Reserva> reservas;
    private List<IObservador> observadores;

    public Livro(int codigo, String titulo, String editora, String autores, String edicao, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.exemplares = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getEditora() {
        return editora;
    }

    @Override
    public String getAutores() {
        return autores;
    }

    @Override
    public String getEdicao() {
        return edicao;
    }

    @Override
    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    @Override
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);

        if (reservas.size() > 2) {
            notificarObservadores();
        }
    }

    @Override
    public void removerReserva(IUsuario usuario) {
        reservas.removeIf(r -> r.getUsuario().getCodigo() == usuario.getCodigo());
    }

    @Override
    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public List<IExemplar> getExemplares() {
        return exemplares;
    }

    @Override
    public void adicionarExemplar(IExemplar exemplar) {
        exemplares.add(exemplar);
    }

    // --- Métodos do padrão Observer (ISujeito) ---
    @Override
    public void registrarObservador(IObservador observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void removerObservador(IObservador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (IObservador obs : observadores) {
            obs.notificar();
        }
    }

    @Override
    public String toString() {
        return "Livro {" +
            "código = " + codigo +
            ", título = '" + titulo + '\'' +
            ", editora = '" + editora + '\'' +
            ", autores = '" + autores + '\'' +
            ", edição = '" + edicao + '\'' +
            ", ano de publicação = " + anoPublicacao +
            ", nº de exemplares = " + exemplares.size() +
            ", nº de reservas = " + reservas.size() +
            ", nº de observadores = " + observadores.size() +
            '}';
    }

}
