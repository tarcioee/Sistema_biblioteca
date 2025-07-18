package Livros;

import Biblioteca.IEntidadeBiblioteca;
import Usuarios.IUsuario;

public interface IExemplar extends IEntidadeBiblioteca {
    int getCodigo();
    ILivro getLivro();
    boolean estaDisponivel();
    void emprestar(IUsuario usuario);
    void devolver();
    IUsuario getUsuarioAtual();
}
