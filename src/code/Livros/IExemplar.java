package Livros;

import Usuarios.IUsuario;

public interface IExemplar {
    int getCodigo();
    ILivro getLivro();
    boolean estaDisponivel();
    void emprestar(IUsuario usuario);
    void devolver();
    IUsuario getUsuarioAtual();
}
