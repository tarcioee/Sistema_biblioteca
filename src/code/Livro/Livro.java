package Livro;
public class Livro {
    private int codigo;
    private String titulo;
    private String editora;
    private List<String> autores;
    private int edicao;
    private int anoPublicacao;
    private List<Exemplar> exemplares;
    private List<Reserva> reservas;
    private List<Observador> observadores;

    // métodos: adicionarExemplar, reservar, notificarObservadores, etc.
}
