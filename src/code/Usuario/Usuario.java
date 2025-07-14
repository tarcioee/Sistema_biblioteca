package Usuario;
import java.util.List;

import Emprestimo.Emprestimo;
import Livro.Livro;
import Reserva.Reserva;

public abstract class Usuario implements IUsuario{
    private int codigo;
    private String nome;
    private int contadorDeNotificacoes;

    public Usuario(int codigoUsuario, String nome) {
        this.codigo = codigoUsuario;
        this.nome = nome;
        this.contadorDeNotificacoes = 0;
    }

    public int getCodigo() {
        return codigo;
    }

     public String getNome() {
        return nome;
    }

    public boolean temAtraso() {
        List<Emprestimo> emprestimosUsuario = this.obterEmprestimos(true);
        return emprestimosUsuario.stream().anyMatch(
                emprestimo -> !emprestimo.isDevolvido() &&
                        emprestimo.getDataDevolucao().before(new Date()));
    }

    

}
 