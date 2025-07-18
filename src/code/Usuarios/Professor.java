package Usuarios;

import Observadores.IObservador;
import java.util.HashSet;
import java.util.Set;

import Emprestimo.Estrategias.EmprestimoProfessor;
import Emprestimo.Estrategias.IEstrategiaEmprestimo;

public class Professor extends Usuario implements IObservador {

    private int contadorNotificacoes;
    private Set<Integer> livrosObservados;

    public Professor(int codigo, String nome) {
        super(codigo, nome);
        this.contadorNotificacoes = 0;
        this.livrosObservados = new HashSet<>();
    }

    @Override
    public int getLimiteEmprestimos() {
        return Integer.MAX_VALUE; // Sem limite
    }

    @Override
    public int getLimiteDiasEmprestimo() {
        return 8;
    }

    @Override
    public void notificar() {
        contadorNotificacoes++;
    }

    public int getContadorNotificacoes() {
        return contadorNotificacoes;
    }

    public void observarLivro(int codigoLivro) {
        livrosObservados.add(codigoLivro);
    }

    public boolean estaObservandoLivro(int codigoLivro) {
        return livrosObservados.contains(codigoLivro);
    }

    public IEstrategiaEmprestimo getEstrategiaEmprestimo() {
		return new EmprestimoProfessor();
	}
}
