package Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Livros.ILivro;
import Usuarios.IUsuario;

public class Repositorio {
    private static Repositorio instancia;

    private List<IUsuario> usuarios;
    private List<ILivro> livros;

    private Repositorio() {
        this.usuarios = new ArrayList<>();
        this.livros = new ArrayList<>();
    }

    public static Repositorio getInstancia() {
        if (instancia == null) {
            instancia = new Repositorio();
        }
        return instancia;
    }

    // --- USUÁRIOS ---
    public void adicionarUsuario(IUsuario usuario) {
        usuarios.add(usuario);
    }

    public IUsuario buscarUsuarioPorCodigo(int codigo) {
        Optional<IUsuario> usuario = usuarios.stream().filter(u -> u.getCodigo() == codigo).findFirst();
        return usuario.orElse(null);
    }

    public List<IUsuario> getUsuarios() {
        return usuarios;
    }

    // --- LIVROS ---
    public void adicionarLivro(ILivro livro) {
        livros.add(livro);
    }

    public ILivro buscarLivroPorCodigo(int codigo) {
        Optional<ILivro> livro = livros.stream().filter(l -> l.getCodigo() == codigo).findFirst();
        return livro.orElse(null);
    }

    public List<ILivro> getLivros() {
        return livros;
    }
}
