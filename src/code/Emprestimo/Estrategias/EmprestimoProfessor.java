package Emprestimo.Estrategias;

import Livros.IExemplar;
import Livros.ILivro;
import Usuarios.IUsuario;

public class EmprestimoProfessor implements IEstrategiaEmprestimo {

    @Override
    public boolean podeEmprestar(IUsuario usuario, ILivro livro) {
        boolean temDisponivel = livro.getExemplares().stream().anyMatch(IExemplar::estaDisponivel);
        boolean estaDevendo = usuario.getEmprestimos().stream().anyMatch(e -> e.estaAtrasado());
        return temDisponivel && !estaDevendo;
    }

    @Override
    public String motivoFalha(IUsuario usuario, ILivro livro) {
        if (livro.getExemplares().stream().noneMatch(IExemplar::estaDisponivel))
            return "Não há exemplares disponíveis.";

        if (usuario.getEmprestimos().stream().anyMatch(e -> e.estaAtrasado()))
            return "Usuário está com empréstimos em atraso.";

        return "Empréstimo não permitido.";
    }
}
