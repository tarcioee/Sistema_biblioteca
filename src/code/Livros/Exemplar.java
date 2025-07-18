package Livros;

import Livros.EstadoExemplar.IExemplarEstado;
import Livros.EstadoExemplar.ExemplarDisponivel;
import Livros.EstadoExemplar.ExemplarEmprestado;
import Usuarios.IUsuario;

public class Exemplar implements IExemplar {
    public ILivro livro;
    private int codigo;
    private IExemplarEstado estado;
    private IUsuario usuarioAtual;

    public Exemplar(int codigo, ILivro livro) {
        this.codigo = codigo;
        this.livro = livro;
        this.estado = new ExemplarDisponivel();
        this.usuarioAtual = null;
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
        return estado.estaDisponivel();
    }

    //daqui pra baixo deveria ter ido pras classes de estado
    @Override
    public void emprestar(IUsuario usuario) {
        this.estado = new ExemplarEmprestado();
        this.usuarioAtual = usuario;
    }

    @Override
    public void devolver() {
        this.estado = new ExemplarDisponivel();
        this.usuarioAtual = null;
    }

    @Override
    public IUsuario getUsuarioAtual() {
        return usuarioAtual;
    }
}
