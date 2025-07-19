package Livros.EstadoExemplar;

import Usuarios.IUsuario;

public interface IExemplarEstado {
    boolean estaDisponivel();
    IExemplarEstado emprestar(IUsuario usuario);
    IExemplarEstado devolver();
    IUsuario getUsuarioAtual();
}