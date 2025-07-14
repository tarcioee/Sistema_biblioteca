package Repositorio;

import Usuario;
import Livro.Livro;

public class Repositorio {
    private static Repositorio instancia;
    private List<Usuario> usuarios;
    private List<Livro> livros;

    private Repositorio() {}

    public static Repositorio getInstancia() {
        if (instancia == null) {
            instancia = new Repositorio();
        }
        return instancia;
    }

    // Métodos: buscarUsuarioPorCodigo(), buscarLivroPorCodigo(), etc.
}
