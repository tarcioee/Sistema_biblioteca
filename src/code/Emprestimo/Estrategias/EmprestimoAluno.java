package Emprestimo.Estrategias;

import Livros.IExemplar;
import Livros.ILivro;
import Usuarios.IUsuario;

public class EmprestimoAluno implements IEstrategiaEmprestimo {

    @Override
    public boolean podeEmprestar(IUsuario usuario, ILivro livro) {
        // requisito 1 checado. Há exemplar disponível?
        boolean temDisponivel = livro.getExemplares().stream().anyMatch(IExemplar::estaDisponivel);
        if (!temDisponivel) return false;

        // 2. Está devendo?
        boolean estaDevendo = usuario.getEmprestimos().stream().anyMatch(e -> e.estaAtrasado());
        if (estaDevendo) return false;

        // 3. Ultrapassou limite de empréstimos?
        long emAberto = usuario.getEmprestimos().stream().filter(e -> e.estaEmAndamento()).count();
        if (emAberto >= usuario.getLimiteEmprestimos()) return false;

        // 4. Já tem esse livro emprestado?
        if (usuario.possuiEmprestimoAtivoDoLivro(livro.getCodigo())) return false;

        // 5. Regras com reserva
        long reservasTotais = livro.getReservas().size();
        long exemplaresDisponiveis = livro.getExemplares().stream().filter(IExemplar::estaDisponivel).count();

        boolean usuarioTemReserva = livro.getReservas().stream()
            .anyMatch(r -> r.getUsuario().getCodigo() == usuario.getCodigo());

        if (reservasTotais >= exemplaresDisponiveis && !usuarioTemReserva) return false;

        return true;
    }

    @Override
    public String motivoFalha(IUsuario usuario, ILivro livro) {
        if (livro.getExemplares().stream().noneMatch(IExemplar::estaDisponivel))
            return "Não há exemplares disponíveis.";

        if (usuario.getEmprestimos().stream().anyMatch(e -> e.estaAtrasado()))
            return "Usuário está com empréstimos em atraso.";

        long emAberto = usuario.getEmprestimos().stream().filter(e -> e.estaEmAndamento()).count();
        if (emAberto >= usuario.getLimiteEmprestimos())
            return "Limite de empréstimos atingido.";

        if (usuario.possuiEmprestimoAtivoDoLivro(livro.getCodigo()))
            return "Usuário já tem um exemplar deste livro emprestado.";

        long reservas = livro.getReservas().size();
        long disponiveis = livro.getExemplares().stream().filter(IExemplar::estaDisponivel).count();
        boolean temReserva = livro.getReservas().stream()
            .anyMatch(r -> r.getUsuario().getCodigo() == usuario.getCodigo());

        if (reservas >= disponiveis && !temReserva)
            return "Livro reservado por outros usuários.";

        return "Empréstimo não permitido.";
    }
}
