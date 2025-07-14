package Emprestimo.Estrategias;

import Livros.ILivro;
import Usuarios.IUsuario;

public interface IEstrategiaEmprestimo {
    boolean podeEmprestar(IUsuario usuario, ILivro livro);
    String motivoFalha(IUsuario usuario, ILivro livro);
}
