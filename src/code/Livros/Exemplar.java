package Livros;

import Livros.EstadoExemplar.IExemplarEstado;
import Livros.EstadoExemplar.ExemplarDisponivel;
import Livros.EstadoExemplar.ExemplarEmprestado;
import Usuarios.IUsuario;

public class Exemplar implements IExemplar {
    public ILivro livro;
    private int codigo;
    private IExemplarEstado estado;

    public Exemplar(int codigo, ILivro livro) {
        this.codigo = codigo;
        this.livro = livro;
        this.estado = new ExemplarDisponivel();
    }

    public ILivro getLivro(){
        return livro;
    }
    
    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public boolean estaDisponivel() {
        // Delega a chamada para o objeto de estado atual
        return estado.estaDisponivel();
    }

    @Override
    public void emprestar(IUsuario usuario) {
        // Delega a ação para o estado e atualiza sua referência para o novo estado
        this.estado = estado.emprestar(usuario);
    }

    @Override
    public void devolver() {
        // Delega a ação para o estado e atualiza sua referência para o novo estado
        this.estado = estado.devolver();
    }


    @Override
    public IUsuario getUsuarioAtual() {
        // Delega a chamada para o objeto de estado atual
        return estado.getUsuarioAtual();
    }
}