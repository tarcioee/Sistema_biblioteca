import Emprestimo.Emprestimo;
import Livro.Livro;

public abstract class Usuario {
    protected int codigo;
    protected String nome;
    protected List<Emprestimo> emprestimos;
    protected List<Reserva> reservas;

    public abstract boolean podeEmprestar(Livro livro);
    public abstract int getDiasEmprestimo();
}
