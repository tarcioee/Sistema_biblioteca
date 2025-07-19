package Livros.EstadoExemplar;

import Usuarios.IUsuario;

public class ExemplarDisponivel implements IExemplarEstado {
    @Override
    public boolean estaDisponivel() {
        return true;
    }

    @Override
    public IExemplarEstado emprestar(IUsuario usuario) {
        // A transição válida ocorre aqui: de Disponível para Emprestado
        return new ExemplarEmprestado(usuario);
    }

    @Override
    public IExemplarEstado devolver() {
        // Não se pode devolver um livro que já está disponível
        throw new IllegalStateException("Não é possível devolver um exemplar que já está disponível.");
    }

    @Override
    public IUsuario getUsuarioAtual() {
        // Um exemplar disponível não tem um usuário associado
        return null;
    }
}