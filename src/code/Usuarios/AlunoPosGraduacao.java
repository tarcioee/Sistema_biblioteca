package Usuarios;

import Emprestimo.Estrategias.EmprestimoAluno;
import Emprestimo.Estrategias.IEstrategiaEmprestimo;

public class AlunoPosGraduacao extends Usuario {

    public AlunoPosGraduacao(int codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }

    @Override
    public int getLimiteDiasEmprestimo() {
        return 5;
    }
    public IEstrategiaEmprestimo getEstrategiaEmprestimo() {
		return new EmprestimoAluno();
	}
}
