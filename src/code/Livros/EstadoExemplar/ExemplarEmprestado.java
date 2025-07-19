package Livros.EstadoExemplar;

import Usuarios.IUsuario;

public class ExemplarEmprestado implements IExemplarEstado {
    private final IUsuario usuarioAtual;

    public ExemplarEmprestado(IUsuario usuario) {
        this.usuarioAtual = usuario;
    }

    @Override
    public boolean estaDisponivel() {
        return false;
    }

    @Override
    public IExemplarEstado emprestar(IUsuario usuario) {
        // Não se pode emprestar um livro que já está emprestado
        throw new IllegalStateException("Não é possível emprestar um exemplar que já está emprestado.");
    }

    @Override
    public IExemplarEstado devolver() {
        // A transição válida ocorre aqui: de Emprestado para Disponível
        return new ExemplarDisponivel();
    }

    @Override
    public IUsuario getUsuarioAtual() {
        return this.usuarioAtual;
    }
}